package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.StatusModel;
import java.util.Optional;


@Repository
public interface StatusDB extends JpaRepository<StatusModel,Long> {
    StatusModel findByStatus(String status);
    Optional<StatusModel> findById(Long id_status);
}