package br.edu.infnet.votingapi.application.voting;

import br.edu.infnet.votingapi.domain.data.model.voting.Candidate;
import br.edu.infnet.votingapi.domain.data.model.voting.PoliticalParty;
import br.edu.infnet.votingapi.domain.util.UuidProvider;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController implements CandidateAPI {
    private final Logger logger;

    public CandidateController(final Logger logger) {
        this.logger = logger;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates() {
        logger.info("Find Candidates...");
        List<Candidate> candidates = List.of(new Candidate(UuidProvider.get(), "Marina", PoliticalParty.PT));
        logger.info("Return Candidates...");
        return ResponseEntity.ok().body(candidates);
    }
}
