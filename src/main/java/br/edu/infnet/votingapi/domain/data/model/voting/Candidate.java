package br.edu.infnet.votingapi.domain.data.model.voting;

import lombok.Data;

@Data
public class Candidate {
    private String uuid;
    private String name;
    private PoliticalParty politicalParty;

    public Candidate(final String uuid, final String name, final PoliticalParty politicalParty) {
        this.uuid = uuid;
        this.name = name;
        this.politicalParty = politicalParty;
    }
}
