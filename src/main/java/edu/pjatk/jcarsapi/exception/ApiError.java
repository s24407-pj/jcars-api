package edu.pjatk.jcarsapi.exception;

import java.time.LocalDateTime;

public record ApiError(
        String path,
        int statusCode,
        String message,
        LocalDateTime localDateTime
) {
}
