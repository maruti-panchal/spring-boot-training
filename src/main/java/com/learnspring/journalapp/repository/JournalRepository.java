package com.learnspring.journalapp.repository;

import com.learnspring.journalapp.entities.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface JournalRepository extends MongoRepository<JournalEntity, ObjectId> {

}
