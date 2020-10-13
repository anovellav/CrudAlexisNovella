package mx.com.invested.crud.CrudAlexisNovella;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import mx.com.invested.crud.model.Empleado;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudAlexisNovellaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmpleadosControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEmpleados() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/empleados",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEmpleadoById() {
		Empleado empleado = restTemplate.getForObject(getRootUrl() + "/empleados/1", Empleado.class);
		System.out.println(empleado.getPrimerNombre());
		assertNotNull(empleado);
	}

	@Test
	public void testCreateEmpleado() {
		Empleado empleado = new Empleado();
		empleado.setEmail("alexis@prueba.com");
		empleado.setPrimerNombre("Alexis");
		empleado.setApPaterno("Novella");
		empleado.setApMaterno("Vidal");

		ResponseEntity<Empleado> postResponse = restTemplate.postForEntity(getRootUrl() + "/empleados", empleado, Empleado.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmpleado() {
		int id = 1;
		Empleado empleado = restTemplate.getForObject(getRootUrl() + "/empleados/" + id, Empleado.class);
		empleado.setPrimerNombre("Dante");
		empleado.setApMaterno("Romero");

		restTemplate.put(getRootUrl() + "/empleados/" + id, empleado);

		Empleado updatedEmpleado = restTemplate.getForObject(getRootUrl() + "/empleados/" + id, Empleado.class);
		assertNotNull(updatedEmpleado);
	}

	@Test
	public void testDeleteEmpleado() {
		int id = 2;
		Empleado empleado = restTemplate.getForObject(getRootUrl() + "/empleados/" + id, Empleado.class);
		assertNotNull(empleado);

		restTemplate.delete(getRootUrl() + "/empleados/" + id);

		try {
			empleado = restTemplate.getForObject(getRootUrl() + "/empleados/" + id, Empleado.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}