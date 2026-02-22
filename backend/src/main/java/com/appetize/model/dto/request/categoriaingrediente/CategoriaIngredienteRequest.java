package com.appetize.model.dto.request.categoriaingrediente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaIngredienteRequest {
    private String nombre;
}
