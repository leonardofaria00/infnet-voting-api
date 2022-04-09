package br.edu.infnet.votingapi.application.data.candidate;

import br.edu.infnet.votingapi.domain.data.model.candidate.PoliticalParty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequest {

    private String name;
    private PoliticalParty politicalParty;

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
