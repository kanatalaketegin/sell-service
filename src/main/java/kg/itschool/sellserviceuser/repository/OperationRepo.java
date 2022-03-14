package kg.itschool.sellserviceuser.repository;

import kg.itschool.sellserviceuser.models.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepo extends JpaRepository<Operation, Long> {

    Operation findOperationById(Long id);
}
