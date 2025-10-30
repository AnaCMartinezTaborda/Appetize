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

    @Column(nullable = false)
    private BigDecimal cantidadHistorica = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal costoHistorico = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal costoUnitario = BigDecimal.ZERO;


    @Enumerated(EnumType.STRING)
    private TipoInventarioEnum tipoInventario;

    @Enumerated(EnumType.STRING)
    private UnidadMedidaEnum unidadMedida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}
