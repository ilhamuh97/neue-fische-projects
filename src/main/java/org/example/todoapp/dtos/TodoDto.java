package org.example.todoapp.dtos;

import lombok.Builder;
import lombok.With;
import org.example.todoapp.enums.Status;

@With
@Builder
public record TodoDto(String description, Status status) {
}
