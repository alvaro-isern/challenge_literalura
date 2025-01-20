package com.challenge.challenge_literalura.service;

public interface IConvertirJson {
    <T> T leerDatos(String json, Class<T> clase);
}
