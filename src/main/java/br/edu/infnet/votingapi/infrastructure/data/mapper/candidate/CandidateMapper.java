package br.edu.infnet.votingapi.infrastructure.data.mapper.candidate;

import br.edu.infnet.votingapi.application.data.candidate.CandidateRequest;
import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.infrastructure.data.model.CandidateDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateMapper {

    @Mappings({
            @Mapping(target = "uuid", source = "candidateDocument.uuid"),
            @Mapping(target = "name", source = "candidateDocument.name"),
            @Mapping(target = "politicalParty", source = "candidateDocument.politicalParty")
    })
    List<Candidate> convertListCandidateDocumentToListCandidate(List<CandidateDocument> candidateDocument);

    @Mappings({
            @Mapping(target = "uuid", source = "candidateDocument.uuid"),
            @Mapping(target = "name", source = "candidateDocument.name"),
            @Mapping(target = "politicalParty", source = "candidateDocument.politicalParty")
    })
    Candidate convertCandidateDocumentToCandidate(CandidateDocument candidateDocument);

    Candidate convertCandidateRequestToCandidate(CandidateRequest candidateRequest);
}
