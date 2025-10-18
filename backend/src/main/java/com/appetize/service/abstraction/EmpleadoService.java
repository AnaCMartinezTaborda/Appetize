package com.appetize.service.abstraction;

import com.appetize.model.dto.request.empleado.EmpleadoRequest;
import com.appetize.model.entity.Empleado;

public interface EmpleadoService {
    void createEmpleado(EmpleadoRequest request);
    Empleado getEmpleado(String id);
}
