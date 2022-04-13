package br.edu.infnet.votingapi.infrastructure.repository.vote;

import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import br.edu.infnet.votingapi.domain.repository.vote.VoteRepository;
import br.edu.infnet.votingapi.infrastructure.data.mapper.vote.VoteMapper;
import br.edu.infnet.votingapi.infrastructure.data.model.vote.VoteDocument;
import br.edu.infnet.votingapi.infrastructure.repository.vote.persistence.VoteMongoRepository;
import br.edu.infnet.votingapi.infrastructure.util.LocalDateTimeProvider;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class VoteRepositoryImpl implements VoteRepository {
    private final VoteMongoRepository voteMongoRepository;
    private final VoteMapper voteMapper;

    public VoteRepositoryImpl(final VoteMongoRepository voteMongoRepository, final VoteMapper voteMapper) {
        this.voteMongoRepository = voteMongoRepository;
        this.voteMapper = voteMapper;
    }

    @Override
    public Vote poll(final Vote vote, final BigInteger amountVote) {
        final Optional<VoteDocument> document = voteMongoRepository.findByUuid(vote.getUuid());
        if (document.isEmpty()) {
            return saveVote(vote, amountVote);
        }
        return updateVote(document);
    }

    private Vote saveVote(final Vote vote, final BigInteger amountVote) {
        final LocalDateTime localDateTime = LocalDateTimeProvider.get();
        VoteDocument voteDocument = voteMapper.convertVoteToVoteDocument(vote, amountVote);
        voteDocument.setCreateDate(localDateTime);
        voteDocument.setUpdateDate(localDateTime);
        return voteMapper.convertVoteDocumentToVote(voteMongoRepository.save(voteDocument));
    }

    private Vote updateVote(final Optional<VoteDocument> document) {
        final BigInteger amountVotes = document.get().getAmountVote().add(BigInteger.ONE);
        document.get().setAmountVote(amountVotes);
        document.get().setUpdateDate(LocalDateTimeProvider.get());
        return voteMapper.convertVoteDocumentToVote(voteMongoRepository.save(document.get()));
    }
}
