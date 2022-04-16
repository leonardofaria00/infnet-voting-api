package br.edu.infnet.votingapi.infrastructure.data.mapper.vote;

import br.edu.infnet.votingapi.application.data.vote.VoteRequest;
import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import br.edu.infnet.votingapi.infrastructure.data.model.candidate.CandidateDocument;
import br.edu.infnet.votingapi.infrastructure.data.model.vote.VoteDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteMapper {
    Vote convertVoteRequestToVote(VoteRequest voteRequest);

    @Mappings({
            @Mapping(target = "uuid", source = "vote.uuid")
    })
    VoteDocument convertVoteToVoteDocument(Vote vote);

    @Mappings({
            @Mapping(target = "uuid", source = "voteDocument.uuid")
    })
    Vote convertVoteDocumentToVote(VoteDocument voteDocument, CandidateDocument candidate);
}
