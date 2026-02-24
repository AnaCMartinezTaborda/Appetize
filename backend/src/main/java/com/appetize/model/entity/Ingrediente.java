package com.appetize.model.entity;

import com.appetize.model.enums.UnidadMedidaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "ingredientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idExterno;

    private String nombre;

    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    private UnidadMedidaEnum unidadMedida;

    private BigDecimal necesario;

    @Enumerated(EnumType.STRING)
    private UnidadMedidaEnum unidadMedidaNecesario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_ingredientes")
    private CategoriaIngrediente categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}
