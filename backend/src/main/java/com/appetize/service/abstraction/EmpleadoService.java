package com.appetize.service.abstraction;

import com.appetize.model.dto.request.empleado.EmpleadoRequest;
import com.appetize.model.dto.response.EmpleadoResponse;
import com.appetize.model.entity.Empleado;

public interface EmpleadoService {
    void createEmpleado(EmpleadoRequest request);
    EmpleadoResponse getEmpleado(String id);
    EmpleadoResponse getPropioEmpleado();
}
