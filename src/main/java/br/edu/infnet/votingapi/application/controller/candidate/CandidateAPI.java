package br.edu.infnet.votingapi.application.controller.candidate;


import br.edu.infnet.votingapi.application.data.candidate.CandidateRequest;
import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CandidateAPI {


    @GetMapping
    ResponseEntity<List<Candidate>> getCandidates() throws Exception;

    @GetMapping(value = "/{uuid}")
    ResponseEntity<Candidate> getCandidateByUuid(@PathVariable String uuid);

    @PostMapping
    ResponseEntity<Candidate> createCandidate(@RequestBody CandidateRequest candidateRequest);

    @PutMapping("/{uuid}")
    ResponseEntity<Candidate> changeCandidate(@RequestBody CandidateRequest candidateRequest, @PathVariable String uuid);
}
