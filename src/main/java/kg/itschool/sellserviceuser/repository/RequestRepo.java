package kg.itschool.sellserviceuser.repository;

import kg.itschool.sellserviceuser.models.entities.Code;
import kg.itschool.sellserviceuser.models.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {

    int countByCodeAndSuccess(Code code, boolean success);
}
