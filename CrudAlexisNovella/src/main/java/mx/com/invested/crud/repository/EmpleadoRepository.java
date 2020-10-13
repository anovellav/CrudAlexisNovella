package mx.com.invested.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.invested.crud.model.Empleado;
 /**
  * 
  * @author Alexis Novella Vidal
  *
  */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
