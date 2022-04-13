package br.edu.infnet.votingapi.infrastructure.repository.vote.persistence;

import br.edu.infnet.votingapi.infrastructure.data.model.vote.VoteDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteMongoRepository extends MongoRepository<VoteDocument, ObjectId> {
    @Query("{ 'uuid' : ?0 }")
    Optional<VoteDocument> findByUuid(String uuid);
}
