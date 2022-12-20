package br.edu.infnet.votingapi.application.controller.candidate;


import br.edu.infnet.votingapi.application.data.candidate.CandidateRequest;
import br.edu.infnet.votingapi.application.util.HttpResponse;
import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CandidateAPI {

    @Operation(
            summary = "Microservice Voting API Spring Rest",
            description = "Permitir que os eleitores votem em seu candidato e veja o candidato mais votado.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_OK,
                            description = HttpResponse.DESCRIPITION_OK,
                            content = @Content(
                                    schema = @Schema(
                                            implementation = Vote.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_NOT_FOUND,
                            description = HttpResponse.DESCRIPITION_NOT_FOUND,
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_INTERNAL_SERVER_ERROR,
                            description = HttpResponse.DESCRIPITION_INTERNAL_SERVER_ERROR,
                            content = @Content
                    )
            }
    )

    @GetMapping
    ResponseEntity<List<Candidate>> getCandidates() throws Exception;

    @GetMapping(value = "/{uuid}")
    ResponseEntity<Candidate> getCandidateByUuid(@PathVariable String uuid);

    @PostMapping
    ResponseEntity<Candidate> createCandidate(@RequestBody CandidateRequest candidateRequest);

    @PutMapping("/{uuid}")
    ResponseEntity<Candidate> changeCandidate(@RequestBody CandidateRequest candidateRequest, @PathVariable String uuid);
}
