package br.com.medvoll.api.models;

public class ErrorRuntime extends RuntimeException {

    public ErrorRuntime(String message) {
        super(message);
    }
}
