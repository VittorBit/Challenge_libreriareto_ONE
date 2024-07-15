package com.proyectochallenge.libreriareto.tools;

public class TextoLimiteAceptado {
    public static String longitudMinimaAceptada(String cadena, int longitudMaxima) {
        if (cadena.length() > longitudMaxima) {
            return cadena;
        } else {
            return cadena.substring(0, longitudMaxima);
        }
    }
}