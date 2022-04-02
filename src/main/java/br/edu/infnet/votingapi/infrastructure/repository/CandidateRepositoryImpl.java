package br.edu.infnet.votingapi.infrastructure.repository;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.data.model.candidate.PoliticalParty;
import br.edu.infnet.votingapi.domain.repository.CandidateRepository;
import br.edu.infnet.votingapi.domain.util.UuidProvider;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidateRepositoryImpl implements CandidateRepository {
    private final Logger logger;

    public CandidateRepositoryImpl(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public List<Candidate> getCandidates() {
        logger.info("Find Candidates...");
        List<Candidate> candidates = List.of(
                new Candidate(UuidProvider.get(), "Marina", PoliticalParty.PSB),
                new Candidate(UuidProvider.get(), "Ciro Gomes", PoliticalParty.PDT)
        );
        logger.info("Return Candidates {}", candidates.get(0).getName());
        return candidates;
    }
}
