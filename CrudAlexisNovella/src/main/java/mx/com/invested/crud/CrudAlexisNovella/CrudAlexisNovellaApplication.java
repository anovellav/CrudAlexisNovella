package mx.com.invested.crud.CrudAlexisNovella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Alexis Novella Vidal
 *
 */
@SpringBootApplication
public class CrudAlexisNovellaApplication {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/**
	 * Método para ejecutar el proyecto WS Rest
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CrudAlexisNovellaApplication.class, args);
	}

}
