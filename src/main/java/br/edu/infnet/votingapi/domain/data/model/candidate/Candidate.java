package br.edu.infnet.votingapi.domain.data.model.candidate;

public class Candidate {

    private String uuid;
    private String name;
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
