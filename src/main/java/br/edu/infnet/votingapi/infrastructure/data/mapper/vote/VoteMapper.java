package br.edu.infnet.votingapi.infrastructure.data.mapper.vote;

import br.edu.infnet.votingapi.application.data.vote.VoteRequest;
import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import br.edu.infnet.votingapi.infrastructure.data.model.vote.VoteDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.math.BigInteger;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteMapper {
    Vote convertVoteRequestToVote(VoteRequest voteRequest);

    @Mappings({
            @Mapping(target = "uuid", source = "vote.uuid"),
            @Mapping(target = "amountVote", source = "amountVote")
    })
    VoteDocument convertVoteToVoteDocument(Vote vote, BigInteger amountVote);

    Vote convertVoteDocumentToVote(VoteDocument voteDocument);
}
