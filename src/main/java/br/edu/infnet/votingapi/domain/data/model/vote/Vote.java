package br.edu.infnet.votingapi.domain.data.model.vote;

import br.edu.infnet.votingapi.domain.data.model.candidate.PoliticalParty;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

public class Vote {
    @NotBlank
    private String uuid;

    private BigInteger amountVote;

    private String name;

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
