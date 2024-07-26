package be.archilios.library.controllers;

public record ServiceError(int status, String error) {
    static ServiceError from(int status, RuntimeException e) {
        return new ServiceError(
                status,
                e.getMessage()
            );
    }
}
