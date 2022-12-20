package br.edu.infnet.votingapi.application.controller.vote;

import br.edu.infnet.votingapi.application.data.vote.VoteRequest;
import br.edu.infnet.votingapi.application.util.HttpResponse;
import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface VoteAPI {
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
                            responseCode = HttpResponse.STATUS_INTERNAL_SERVER_ERROR,
                            description = HttpResponse.DESCRIPITION_INTERNAL_SERVER_ERROR,
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Vote> poll(VoteRequest uuid);
}
