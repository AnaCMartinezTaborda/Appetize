package com.appetize.model.entity;

import com.appetize.model.enums.TipoInventarioEnum;
import com.appetize.model.enums.UnidadMedidaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "id_externo")
    private Long idExterno;

    @Column(nullable = false)
    private String nombre;

    private BigDecimal cantidad;

    @Column(nullable = false)
    private BigDecimal cantidadHistorica;

    @Column(nullable = false)
    private BigDecimal costoHistorico;

    @Column(nullable = false)
    private BigDecimal costoUnitario;

    @Enumerated(EnumType.STRING)
    private TipoInventarioEnum tipoInventario;

    @Enumerated(EnumType.STRING)
    private UnidadMedidaEnum unidadMedida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}
