package br.edu.infnet.votingapi.infrastructure.repository;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.exception.BusinessException;
import br.edu.infnet.votingapi.domain.repository.CandidateRepository;
import br.edu.infnet.votingapi.domain.util.UuidProvider;
import br.edu.infnet.votingapi.infrastructure.data.mapper.CandidateDocumentMapper;
import br.edu.infnet.votingapi.infrastructure.data.mapper.CandidateMapper;
import br.edu.infnet.votingapi.infrastructure.data.model.CandidateDocument;
import br.edu.infnet.votingapi.infrastructure.repository.persistence.CandidateMongoRepository;
import br.edu.infnet.votingapi.infrastructure.util.LocalDateTimeProvider;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.String.format;

@Component
public class CandidateRepositoryImpl implements CandidateRepository {
    private final Logger logger;
    private final CandidateMapper candidateMapper;
    private final CandidateDocumentMapper candidateDocumentMapper;
    private final CandidateMongoRepository candidateMongoRepository;

    public CandidateRepositoryImpl(
            final Logger logger,
            final CandidateMapper candidateMapper,
            final CandidateDocumentMapper candidateDocumentMapper,
            final CandidateMongoRepository candidateMongoRepository) {
        this.logger = logger;
        this.candidateMapper = candidateMapper;
        this.candidateDocumentMapper = candidateDocumentMapper;
        this.candidateMongoRepository = candidateMongoRepository;
    }

    @Override
    public List<Candidate> getCandidates() {
        logger.info("-> Find Candidates...");
        try {
            final List<CandidateDocument> documentList = candidateMongoRepository.findAll();
            final List<Candidate> candidates = candidateMapper.convertListCandidateDocumentToListCandidate(documentList);
            logger.info("Returning {} Candidates", candidates.size());
            return candidates;
        } catch (final Exception exception) {
            logger.error("[Error] to find Candidate: {}", exception.getMessage());
            throw new BusinessException(format("[Error] to find Candidate: %s", exception.getMessage()));
        }
    }

    @Override
    public Candidate createCandidate(final Candidate candidate) {
        try {
            final CandidateDocument candidateDocument = candidateDocumentMapper.convertCandidateToCandidateDocument(candidate);
            final LocalDateTime localDateTime = LocalDateTimeProvider.get();
            candidateDocument.setUuid(UuidProvider.get());
            candidateDocument.setCreatedDate(localDateTime);
            candidateDocument.setUpdatedDate(localDateTime);

            return candidateMapper.convertCandidateDocumentToCandidate(candidateMongoRepository.insert(candidateDocument));
        } catch (final Exception exception) {
            logger.error("[Error] to INSERT Candidate: {}", exception.getMessage());
            throw new BusinessException(format("[Error] to INSERT Candidate: %s", exception.getMessage()));
        }
    }

    @Override
    public Candidate changeCandidate(final Candidate candidate, final String uuid) {
        try {
            final CandidateDocument candidateDocument = findCandidateByUuid(uuid);
            final CandidateDocument document = candidateDocumentMapper.convertCandidateDocumentToChange(candidateDocument, candidate);
            document.setUpdatedDate(LocalDateTimeProvider.get());
            return candidateMapper.convertCandidateDocumentToCandidate(candidateMongoRepository.save(document));
        } catch (final Exception exception) {
            logger.error("[Error] to change Candidate uuid: {} -> {}", uuid, exception.getMessage());
            throw new BusinessException(format("[Error] to change Candidate uuid: %s -> %s", uuid, exception.getMessage()));
        }
    }

    private CandidateDocument findCandidateByUuid(final String uuid) {
        try {
            return candidateMongoRepository.findByUuid(uuid);
        } catch (final Exception exception) {
            logger.error("[Error] to find Candidate uuid: {} -> {}", uuid, exception.getMessage());
            throw new BusinessException(format("[Error] to find Candidate uuid: %s -> %s", uuid, exception.getMessage()));
        }
    }
}
