package com.appetize.model.entity;

import com.appetize.model.enums.TipoInventarioEnum;
import com.appetize.model.enums.UnidadMedidaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_externo", nullable = false)
    private Long idExterno;

    @Column(nullable = false)
    private String nombre;

    private BigDecimal cantidad;
    private BigDecimal precioUnitario;

    @Enumerated(EnumType.STRING)
    private TipoInventarioEnum tipoInventario;

    @Enumerated(EnumType.STRING)
    private UnidadMedidaEnum unidadMedida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}
