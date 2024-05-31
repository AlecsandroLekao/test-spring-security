package br.com.medvoll.api.models;

import java.time.LocalDateTime;

public record ErrorClass(LocalDateTime localDateTime, Integer status, String error, String message, String path) {
}
