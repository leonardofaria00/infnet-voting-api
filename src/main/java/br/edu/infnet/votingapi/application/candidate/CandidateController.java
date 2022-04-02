package br.edu.infnet.votingapi.application.candidate;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController implements CandidateAPI {
    private final CandidateService candidateService;

    public CandidateController(final CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates() {
        List<Candidate> candidates = candidateService.getCandidates();

        return ResponseEntity.ok().body(candidates);
    }
}
