package br.edu.infnet.votingapi.infrastructure.repository;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.exception.BusinessException;
import br.edu.infnet.votingapi.domain.repository.CandidateRepository;
import br.edu.infnet.votingapi.infrastructure.data.mapper.CandidateMapper;
import br.edu.infnet.votingapi.infrastructure.data.model.CandidateDocument;
import br.edu.infnet.votingapi.infrastructure.repository.persistence.CandidateMongoRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
public class CandidateRepositoryImpl implements CandidateRepository {
    private final Logger logger;
    private final CandidateMapper candidateMapper;
    private final CandidateMongoRepository candidateMongoRepository;

    public CandidateRepositoryImpl(
            final Logger logger,
            final CandidateMapper candidateMapper,
            final CandidateMongoRepository candidateMongoRepository) {
        this.logger = logger;
        this.candidateMapper = candidateMapper;
        this.candidateMongoRepository = candidateMongoRepository;
    }

    @Override
    public List<Candidate> getCandidates() {
        logger.info("-> Find Candidates...");
        try {
            final List<CandidateDocument> documentList = candidateMongoRepository.findAll();
            final List<Candidate> candidates = candidateMapper.convertCandidateDocumentToCandidate(documentList);
            logger.info("Returning {} Candidates", candidates.size());
            return candidates;
        } catch (final Exception exception) {
            logger.error("[Error] to find Candidate: {}", exception.getMessage());
            throw new BusinessException(format("[Error] to find Candidate: %s", exception.getMessage()));
        }
    }
}
