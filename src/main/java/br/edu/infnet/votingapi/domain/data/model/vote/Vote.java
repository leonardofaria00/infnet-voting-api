package br.edu.infnet.votingapi.domain.data.model.vote;

import javax.validation.constraints.NotBlank;

public class Vote {
    @NotBlank
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
