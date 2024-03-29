package br.edu.infnet.votingapi.infrastructure.repository.candidate;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.exception.BusinessException;
import br.edu.infnet.votingapi.domain.repository.candidate.CandidateRepository;
import br.edu.infnet.votingapi.domain.util.UuidProvider;
import br.edu.infnet.votingapi.infrastructure.data.mapper.candidate.CandidateDocumentMapper;
import br.edu.infnet.votingapi.infrastructure.data.mapper.candidate.CandidateMapper;
import br.edu.infnet.votingapi.infrastructure.data.model.candidate.CandidateDocument;
import br.edu.infnet.votingapi.infrastructure.repository.candidate.persistence.CandidateMongoRepository;
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
            logger.info("[SUCCESS] to find list of candidates");
            return candidates;
        } catch (final Exception exception) {
            logger.error("[Error] to find candidates: {}", exception.getMessage());
            throw new BusinessException(format("[Error] to find Candidates: %s", exception.getMessage()));
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
            final CandidateDocument document = candidateMongoRepository.insert(candidateDocument);
            logger.info("[SUCCESS] to insert candidate with name: {}", document.getName());
            return candidateMapper.convertCandidateDocumentToCandidate(document);
        } catch (final Exception exception) {
            logger.error("[Error] to INSERT candidate: {}", exception.getMessage());
            throw new BusinessException(format("[Error] to INSERT candidate: %s", exception.getMessage()));
        }
    }

    @Override
    public Candidate changeCandidate(final Candidate candidate, final String uuid) {
        try {
            final CandidateDocument candidateDocument = getCandidateDocumentByUuid(uuid);
            final CandidateDocument document = candidateDocumentMapper.convertCandidateDocumentToChange(candidateDocument, candidate);
            document.setUpdatedDate(LocalDateTimeProvider.get());
            final CandidateDocument save = candidateMongoRepository.save(document);
            logger.info("[SUCCESS] to change candidate with name: {} to {}", candidateDocument.getName(), save.getName());
            return candidateMapper.convertCandidateDocumentToCandidate(save);
        } catch (final Exception exception) {
            logger.error("[Error] to change candidate uuid: {} -> {}", uuid, exception.getMessage());
            throw new BusinessException(format("[Error] to change candidate uuid: %s -> %s", uuid, exception.getMessage()));
        }
    }

    @Override
    public Candidate getCandidateByUuid(final String uuid) {
        final CandidateDocument candidateDocument = getCandidateDocumentByUuid(uuid);
        return candidateMapper.convertCandidateDocumentToCandidate(candidateDocument);
    }

    private CandidateDocument getCandidateDocumentByUuid(final String uuid) {
        try {
            return candidateMongoRepository.findByUuid(uuid);
        } catch (final Exception exception) {
            logger.error("[Error] to find candidate uuid: {} -> {}", uuid, exception.getMessage());
            throw new BusinessException(format("[Error] to find candidate uuid: %s -> %s", uuid, exception.getMessage()));
        }
    }
}
