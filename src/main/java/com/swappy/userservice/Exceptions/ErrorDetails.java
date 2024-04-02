package com.swappy.userservice.Exceptions;

import lombok.*;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;
}
