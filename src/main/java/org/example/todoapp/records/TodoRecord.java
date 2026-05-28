package org.example.todoapp.records;

import lombok.Builder;
import lombok.With;
import org.example.todoapp.enums.Status;

@With
@Builder
public record TodoRecord(String id, String description, Status status) {
}
