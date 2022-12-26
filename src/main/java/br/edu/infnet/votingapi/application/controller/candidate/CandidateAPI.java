package br.edu.infnet.votingapi.application.controller.candidate;


import br.edu.infnet.votingapi.application.data.candidate.CandidateRequest;
import br.edu.infnet.votingapi.application.util.HttpResponse;
import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
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
            description = "Método get para obter informações dos candidatos.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_OK,
                            description = HttpResponse.DESCRIPTION_OK,
                            content = @Content(
                                    schema = @Schema(
                                            implementation = Candidate.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_NOT_FOUND,
                            description = HttpResponse.DESCRIPTION_NOT_FOUND,
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_INTERNAL_SERVER_ERROR,
                            description = HttpResponse.DESCRIPTION_INTERNAL_SERVER_ERROR,
                            content = @Content
                    )
            }
    )

    @GetMapping
    ResponseEntity<List<Candidate>> getCandidates() throws Exception;

    @Operation(
            summary = "Microservice Voting API Spring Rest",
            description = "Método get que retorna um identificador exclusivo do candidato.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_OK,
                            description = HttpResponse.DESCRIPTION_OK,
                            content = @Content(
                                    schema = @Schema(
                                            implementation = Candidate.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_NOT_FOUND,
                            description = HttpResponse.DESCRIPTION_NOT_FOUND,
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_INTERNAL_SERVER_ERROR,
                            description = HttpResponse.DESCRIPTION_INTERNAL_SERVER_ERROR,
                            content = @Content
                    )
            }
    )


    @GetMapping(value = "/{uuid}")
    ResponseEntity<Candidate> getCandidateByUuid(@PathVariable String uuid);

    @Operation(
            summary = "Microservice Voting API Spring Rest",
            description = "Método post para criação de candidatos.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_OK,
                            description = HttpResponse.DESCRIPTION_OK,
                            content = @Content(
                                    schema = @Schema(
                                            implementation = Candidate.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_NOT_FOUND,
                            description = HttpResponse.DESCRIPTION_NOT_FOUND,
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_INTERNAL_SERVER_ERROR,
                            description = HttpResponse.DESCRIPTION_INTERNAL_SERVER_ERROR,
                            content = @Content
                    )
            }
    )

    @PostMapping
    ResponseEntity<Candidate> createCandidate(@RequestBody CandidateRequest candidateRequest);

    @Operation(
            summary = "Microservice Voting API Spring Rest",
            description = "Método put para realizar atualização de candidatos.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_OK,
                            description = HttpResponse.DESCRIPTION_OK,
                            content = @Content(
                                    schema = @Schema(
                                            implementation = Candidate.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_NOT_FOUND,
                            description = HttpResponse.DESCRIPTION_NOT_FOUND,
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = HttpResponse.STATUS_INTERNAL_SERVER_ERROR,
                            description = HttpResponse.DESCRIPTION_INTERNAL_SERVER_ERROR,
                            content = @Content
                    )
            }
    )

    @PutMapping("/{uuid}")
    ResponseEntity<Candidate> changeCandidate(@RequestBody CandidateRequest candidateRequest, @PathVariable String uuid);
}
