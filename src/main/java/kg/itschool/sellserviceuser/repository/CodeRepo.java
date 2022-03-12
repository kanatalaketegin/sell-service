package kg.itschool.sellserviceuser.repository;

import kg.itschool.sellserviceuser.models.entities.Code;
import kg.itschool.sellserviceuser.models.entities.User;
import kg.itschool.sellserviceuser.models.enums.CodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<Code, Long> {
    Code findByUserAndCodeStatus(User mapToUser, CodeStatus aNew);
}
