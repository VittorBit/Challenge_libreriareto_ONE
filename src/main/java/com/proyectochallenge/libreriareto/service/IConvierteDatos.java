package com.proyectochallenge.libreriareto.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
