package br.edu.infnet.votingapi.domain.data.model.vote;

import br.edu.infnet.votingapi.domain.data.model.candidate.PoliticalParty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

public class Vote {
    @Schema(description = "Vote uuid", example = "f49adda3-25ee-4521-84fc-1a257a25d55d")
    @NotBlank
    private String uuid;

    @Schema(description = "Vote Amount", example = "100")
    private BigInteger amountVote;

    @Schema(description = "Name", example = "Leonardo")
    private String name;

    @Schema(description = "Political Party")
    private PoliticalParty politicalParty;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigInteger getAmountVote() {
        return amountVote;
    }

    public void setAmountVote(BigInteger amountVote) {
        this.amountVote = amountVote;
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
