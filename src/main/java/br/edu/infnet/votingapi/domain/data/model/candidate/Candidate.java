package br.edu.infnet.votingapi.domain.data.model.candidate;

import lombok.Getter;

public class Candidate {
    @Getter
    private final String uuid;
    @Getter
    private final String name;
    @Getter
    private final PoliticalParty politicalParty;

    public Candidate(final String uuid, final String name, final PoliticalParty politicalParty) {
        this.uuid = uuid;
        this.name = name;
        this.politicalParty = politicalParty;
    }
}
