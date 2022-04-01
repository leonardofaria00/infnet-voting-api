package br.edu.infnet.votingapi.application.candidate;


import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateAPI {
    ResponseEntity<List<Candidate>> getCandidates();
}
