package mx.com.invested.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import mx.com.invested.crud.exception.ResourceNotFoundException;
import mx.com.invested.crud.model.Empleado;
import mx.com.invested.crud.repository.EmpleadoRepository;
/**
 * 
 * @author Alexis Novella Vidal
 *
 */
@RestController
@RequestMapping("/crudEmpleados")
public class EmpleadoController {
	
	@Autowired
    private EmpleadoRepository empleadoRepository;

	/**
	 * Método que devuelve todos los empleados
	 * @return
	 */
    @GetMapping("/empleados")
    public List < Empleado > getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    /**
     * Método que busca un empleado por ID
     * @param empleadoId
     * @return
     * @throws ResourceAccessException
     */
    @GetMapping("/empleados/{id}")
    public ResponseEntity < Empleado > getEmpleadoById(@PathVariable(value = "id") Long empleadoId)
    throws ResourceNotFoundException {
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + empleadoId));
        return ResponseEntity.ok().body(empleado);
    }

    /**
     * Método para dar de alta un empleado
     * @param empleado
     * @return
     */
    @PostMapping("/empleados")
    public Empleado createEmpleado(@Validated @RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    /**
     * Método que actualiza los datos de un empleado
     * @param empleadoId
     * @param empleadoDetails
     * @return
     * @throws ResourceAccessException
     */
    @PutMapping("/empleados/{id}")
    public ResponseEntity < Empleado > updateEmpleado(@PathVariable(value = "id") Long empleadoId,
        @Validated @RequestBody Empleado empleadoDetails) throws ResourceNotFoundException {
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new ResourceNotFoundException("El empleado con el id :: " + empleadoId + ", no fué encontrado"));

        empleado.setEmail(empleadoDetails.getEmail());
        empleado.setPrimerNombre(empleadoDetails.getPrimerNombre());
        empleado.setSegundoNombre(empleadoDetails.getSegundoNombre());
        empleado.setApPaterno(empleadoDetails.getApPaterno());
        empleado.setApMaterno(empleadoDetails.getApMaterno());
        final Empleado updatedEmpleado = empleadoRepository.save(empleado);
        return ResponseEntity.ok(updatedEmpleado);
    }

    /**
     * Método que elimina un empleado
     * @param empleadoId
     * @return
     * @throws ResourceAccessException
     */
    @DeleteMapping("/empleados/{id}")
    public Map < String, Boolean > deleteEmpleado(@PathVariable(value = "id") Long empleadoId)
    throws ResourceNotFoundException {
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new ResourceNotFoundException("El empleado con el id :: " + empleadoId + ", no fué encontrado"));

        empleadoRepository.delete(empleado);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
