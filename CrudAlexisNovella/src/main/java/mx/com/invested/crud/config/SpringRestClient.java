package mx.com.invested.crud.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import mx.com.invested.crud.model.Empleado;

public class SpringRestClient {

	private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/crudEmpleados/empleados";
	private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/crudEmpleados/empleados/{id}";
	private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/crudEmpleados/empleados";
	private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/crudEmpleados/empleados/{id}";
	private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/crudEmpleados/empleados/{id}";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();
		
		// Step1: first create a new empleado
		springRestClient.createEmpleado();
		
		// Step 2: get new created empleado from step1
		springRestClient.getEmpleadoById();
		
		// Step3: get all empleados
		springRestClient.getEmpleados();
		
		// Step4: Update empleado with id = 1
		springRestClient.updateEmpleado();
		
		// Step5: Delete empleado with id = 1
		springRestClient.deleteEmpleado();
	}

	private void getEmpleados() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getEmpleadoById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		Empleado result = restTemplate.getForObject(GET_EMPLOYEE_ENDPOINT_URL, Empleado.class, params);

		System.out.println(result);
	}

	private void createEmpleado() {

		Empleado newEmpleado = new Empleado("Alexis", "", "Novella", "Vidal", "alexis@prueba.com");

		RestTemplate restTemplate = new RestTemplate();
		Empleado result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, newEmpleado, Empleado.class);

		System.out.println(result);
	}

	private void updateEmpleado() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		Empleado updatedEmpleado = new Empleado("Dante", "Hasen", "Novella", "Romero", "dante@prueba.com");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updatedEmpleado, params);
	}

	private void deleteEmpleado() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
	}
}