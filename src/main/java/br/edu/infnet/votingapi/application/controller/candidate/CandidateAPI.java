package br.edu.infnet.votingapi.application.controller.candidate;


import br.edu.infnet.votingapi.application.data.candidate.CandidateRequest;
import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateAPI {
    ResponseEntity<List<Candidate>> getCandidates() throws Exception;

    ResponseEntity<Candidate> createCandidate(CandidateRequest candidateRequest);

    ResponseEntity<Candidate> changeCandidate(CandidateRequest candidateRequest, String uuid);
}
