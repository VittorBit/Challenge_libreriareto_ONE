package com.proyectochallenge.libreriareto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConsultarAPI (
        @JsonAlias("count")  int enumerar,
        @JsonAlias("next")  String siguiente,
        @JsonAlias("previous")  String anterior,
        @JsonAlias("results") List<DatosLibro> resultados) {
}
