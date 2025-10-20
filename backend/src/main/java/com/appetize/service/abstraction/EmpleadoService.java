package com.appetize.service.abstraction;

import com.appetize.model.dto.request.empleado.EmpleadoRequest;
import com.appetize.model.dto.request.empleado.UpdatePasswordRequest;
import com.appetize.model.dto.response.EmpleadoResponse;

import java.util.List;

public interface EmpleadoService {
    void createEmpleado(EmpleadoRequest request);
    EmpleadoResponse getEmpleadoById(String id);
    EmpleadoResponse getPropioEmpleado();
    List<EmpleadoResponse> getAllEmpleadosByRestaurante();
    void updatePasswordPropia(UpdatePasswordRequest request);
    void updateEmpleado(EmpleadoRequest request, String id);
}
