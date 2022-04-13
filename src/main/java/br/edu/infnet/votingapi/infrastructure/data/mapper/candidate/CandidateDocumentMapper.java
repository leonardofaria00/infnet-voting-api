package br.edu.infnet.votingapi.infrastructure.data.mapper.candidate;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.infrastructure.data.model.CandidateDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateDocumentMapper {

    CandidateDocument convertCandidateToCandidateDocument(Candidate candidate);

    @Mappings({
            @Mapping(target = "uuid", source = "candidateDocument.uuid"),
            @Mapping(target = "name", source = "candidate.name"),
            @Mapping(target = "politicalParty", source = "candidate.politicalParty")
    })
    CandidateDocument convertCandidateDocumentToChange(CandidateDocument candidateDocument, Candidate candidate);
}
