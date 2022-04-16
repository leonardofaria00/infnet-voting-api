package br.edu.infnet.votingapi.infrastructure.repository.vote;

import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import br.edu.infnet.votingapi.domain.exception.BusinessException;
import br.edu.infnet.votingapi.domain.repository.vote.VoteRepository;
import br.edu.infnet.votingapi.infrastructure.data.mapper.vote.VoteMapper;
import br.edu.infnet.votingapi.infrastructure.data.model.candidate.CandidateDocument;
import br.edu.infnet.votingapi.infrastructure.data.model.vote.VoteDocument;
import br.edu.infnet.votingapi.infrastructure.repository.candidate.persistence.CandidateMongoRepository;
import br.edu.infnet.votingapi.infrastructure.repository.vote.persistence.VoteMongoRepository;
import br.edu.infnet.votingapi.infrastructure.util.LocalDateTimeProvider;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.lang.String.format;

@Component
public class VoteRepositoryImpl implements VoteRepository {
    private final VoteMongoRepository voteMongoRepository;
    private final CandidateMongoRepository candidateMongoRepository;
    private final VoteMapper voteMapper;
    private final Logger logger;

    public VoteRepositoryImpl(
            final VoteMongoRepository voteMongoRepository,
            final CandidateMongoRepository candidateMongoRepository,
            final VoteMapper voteMapper,
            final Logger logger) {
        this.voteMongoRepository = voteMongoRepository;
        this.candidateMongoRepository = candidateMongoRepository;
        this.voteMapper = voteMapper;
        this.logger = logger;
    }

    @Override
    public Vote poll(final Vote vote) {
        final Optional<VoteDocument> document = voteMongoRepository.findByUuid(vote.getUuid());
        if (document.isEmpty()) {
            return saveVote(vote);
        }
        return updateVote(document);
    }

    private Vote saveVote(final Vote vote) {
        try {
            final LocalDateTime localDateTime = LocalDateTimeProvider.get();
            VoteDocument voteDocument = voteMapper.convertVoteToVoteDocument(vote);
            voteDocument.setAmountVote(BigInteger.ONE);
            voteDocument.setCreateDate(localDateTime);
            voteDocument.setUpdateDate(localDateTime);
            final VoteDocument document = voteMongoRepository.save(voteDocument);
            logger.info("[SUCCESS] to insert entering candidate vote with uuid: {}", document.getUuid());
            final CandidateDocument candidate = getCandidate(document);
            return voteMapper.convertVoteDocumentToVote(document, candidate);
        } catch (final Exception exception) {
            logger.error("[Error] to save vote for candidate with uuid: {} -> error: {}", vote.getUuid(), exception.getMessage());
            throw new BusinessException(format("[Error] to save vote for candidate: %s", exception.getMessage()));
        }
    }

    private Vote updateVote(final Optional<VoteDocument> document) {
        try {
            if (document.isPresent()) {
                final BigInteger amountVotes = document.get().getAmountVote().add(BigInteger.ONE);
                document.get().setAmountVote(amountVotes);
                document.get().setUpdateDate(LocalDateTimeProvider.get());
            }
            final VoteDocument voteDocument = voteMongoRepository.save(document.get());
            logger.info("[SUCCESS] to update entering candidate vote with uuid: {}", voteDocument.getUuid());
            final CandidateDocument candidate = getCandidate(voteDocument);
            return voteMapper.convertVoteDocumentToVote(voteDocument, candidate);
        } catch (final Exception exception) {
            logger.error("[Error] to update vote for candidate with uuid: {} -> error: {}", document.get().getUuid(), exception.getMessage());
            throw new BusinessException(format("[Error] to update vote for candidate: %s", exception.getMessage()));
        }
    }

    private CandidateDocument getCandidate(final VoteDocument voteDocument) {
        try {
            return candidateMongoRepository.findByUuid(voteDocument.getUuid());
        } catch (final Exception exception) {
            logger.error("[Error] to find candidate with uuid: {} -> error: {}", voteDocument.getUuid(), exception.getMessage());
            throw new BusinessException(format("[Error] to find candidate: %s", exception.getMessage()));
        }
    }
}
