package br.edu.infnet.votingapi.infrastructure.data.mapper;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.infrastructure.data.model.CandidateDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateMapper {

    @Mappings({
            @Mapping(target = "uuid", source = "candidateDocument.uuid"),
            @Mapping(target = "name", source = "candidateDocument.name"),
            @Mapping(target = "politicalParty", source = "candidateDocument.politicalParty")
    })
    Candidate convertCandidateDocumentToCandidate(CandidateDocument candidateDocument);
}
