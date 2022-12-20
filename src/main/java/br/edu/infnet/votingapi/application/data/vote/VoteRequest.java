package br.edu.infnet.votingapi.application.data.vote;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {
    @Schema(description = "Vote uuid", example = "f49adda3-25ee-4521-84fc-1a257a25d55d")
    @NotBlank
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
