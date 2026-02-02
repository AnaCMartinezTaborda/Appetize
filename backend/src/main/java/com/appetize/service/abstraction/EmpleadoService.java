package com.appetize.service.abstraction;

import com.appetize.model.dto.request.empleado.EmpleadoRequest;
import com.appetize.model.dto.request.empleado.UpdatePasswordRequest;
import com.appetize.model.dto.response.EmpleadoResponse;
import com.appetize.model.dto.response.PaginatedResponse;

public interface EmpleadoService {
    void createEmpleado(EmpleadoRequest request);
    EmpleadoResponse getEmpleadoById(String id);
    EmpleadoResponse getPropioEmpleado();
    PaginatedResponse<EmpleadoResponse> getAllEmpleadosByRestaurantePaged(int page, int size);
    void updatePasswordPropia(UpdatePasswordRequest request);
    void updateEmpleado(EmpleadoRequest request, String id);
}
