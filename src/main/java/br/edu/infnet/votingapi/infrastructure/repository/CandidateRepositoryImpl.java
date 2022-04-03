package br.edu.infnet.votingapi.infrastructure.repository;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.data.model.candidate.PoliticalParty;
import br.edu.infnet.votingapi.domain.repository.CandidateRepository;
import br.edu.infnet.votingapi.domain.util.UuidProvider;
import br.edu.infnet.votingapi.infrastructure.data.mapper.CandidateMapper;
import br.edu.infnet.votingapi.infrastructure.data.model.CandidateDocument;
import br.edu.infnet.votingapi.infrastructure.util.LocalDateTimeProvider;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidateRepositoryImpl implements CandidateRepository {
    private final Logger logger;
    private final CandidateMapper candidateMapper;

    public CandidateRepositoryImpl(final Logger logger, final CandidateMapper candidateMapper) {
        this.logger = logger;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public List<Candidate> getCandidates() {
        logger.info("Find Candidates...");
        CandidateDocument candidateDocument = CandidateDocument.builder()
                .uuid(UuidProvider.get())
                .name("Marina")
                .politicalParty(PoliticalParty.PSB.name())
                .createdDate(LocalDateTimeProvider.get())
                .updatedDate(LocalDateTimeProvider.get())
                .build();
        Candidate candidate = candidateMapper.convertCandidateDocumentToCandidate(candidateDocument);
        List<Candidate> candidates = List.of(candidate);
        logger.info("Return Candidates {}", candidates.get(0).getName());
        return candidates;
    }
}
