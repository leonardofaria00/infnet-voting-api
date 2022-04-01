package br.edu.infnet.votingapi.application.voting;


import br.edu.infnet.votingapi.domain.data.model.voting.Candidate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateAPI {
    ResponseEntity<List<Candidate>> getCandidates();
}
