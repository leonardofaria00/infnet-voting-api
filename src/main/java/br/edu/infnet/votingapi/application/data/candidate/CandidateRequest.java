package br.edu.infnet.votingapi.application.data.candidate;

import br.edu.infnet.votingapi.domain.data.model.candidate.PoliticalParty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequest {

    @Schema(description = "Candidate name", example = "Tiririca")
    private String name;

    @Schema(description = "Political Party")
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
