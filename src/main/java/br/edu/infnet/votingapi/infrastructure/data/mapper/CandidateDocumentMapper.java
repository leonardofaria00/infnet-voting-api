package br.edu.infnet.votingapi.infrastructure.data.mapper;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.infrastructure.data.model.CandidateDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateDocumentMapper {

    CandidateDocument convertCandidateToCandidateDocument(Candidate candidate);
}
