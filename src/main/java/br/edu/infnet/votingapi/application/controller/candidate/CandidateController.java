package br.edu.infnet.votingapi.application.controller.candidate;

import br.edu.infnet.votingapi.application.data.candidate.CandidateRequest;
import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.service.candidate.CandidateService;
import br.edu.infnet.votingapi.infrastructure.data.mapper.candidate.CandidateMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voting/v1/candidate")
public class CandidateController implements CandidateAPI {
    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;

    public CandidateController(final CandidateService candidateService, final CandidateMapper candidateMapper) {
        this.candidateService = candidateService;
        this.candidateMapper = candidateMapper;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates() throws Exception {
        return ResponseEntity.ok().body(candidateService.getCandidates());
    }

    @Override
    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody final CandidateRequest candidateRequest) {
        final Candidate candidate = candidateMapper.convertCandidateRequestToCandidate(candidateRequest);
        return ResponseEntity.ok().body(candidateService.createCandidate(candidate));
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<Candidate> changeCandidate(
            @RequestBody final CandidateRequest candidateRequest,
            @PathVariable final String uuid) {
        final Candidate candidate = candidateMapper.convertCandidateRequestToCandidate(candidateRequest);
        return ResponseEntity.ok().body(candidateService.changeCandidate(candidate, uuid));
    }
}
