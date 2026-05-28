package org.example.todoapp.repos;

import org.example.todoapp.records.TodoRecord;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepo extends MongoRepository<TodoRecord,String> {
    @NullMarked Optional<TodoRecord> findById(String id);
}
