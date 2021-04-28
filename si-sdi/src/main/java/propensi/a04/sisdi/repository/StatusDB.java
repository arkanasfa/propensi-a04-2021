package propensi.a04.sisdi.repository;

import propensi.a04.sisdi.model.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StatusDB extends JpaRepository<StatusModel,Long>{
    Optional<StatusModel> findById(Long id_status);
}

