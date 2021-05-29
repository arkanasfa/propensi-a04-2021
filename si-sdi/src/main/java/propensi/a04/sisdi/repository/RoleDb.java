package propensi.a04.sisdi.repository;

import propensi.a04.sisdi.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel,Long> {
    RoleModel findRoleModelById(Long id);
}

