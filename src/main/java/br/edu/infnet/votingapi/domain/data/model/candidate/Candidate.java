package br.edu.infnet.votingapi.domain.data.model.candidate;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class Candidate {

    @Schema(description = "Vote uuid", example = "f49adda3-25ee-4521-84fc-1a257a25d55d")
    private String uuid;

    @Schema(description = "Name", example = "Tiririca")
    @NotNull
    private String name;

    @Schema(description = "Political Party", example = "PSOL")
    @NotNull
    private PoliticalParty politicalParty;

    public Candidate(final String uuid, final String name, final PoliticalParty politicalParty) {
        this.uuid = uuid;
        this.name = name;
        this.politicalParty = politicalParty;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PoliticalParty getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(PoliticalParty politicalParty) {
        this.politicalParty = politicalParty;
    }
}
