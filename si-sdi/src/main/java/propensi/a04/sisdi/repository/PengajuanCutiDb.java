package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.PengajuanCutiModel;
import propensi.a04.sisdi.model.StatusModel;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PengajuanCutiDb extends JpaRepository<PengajuanCutiModel, Long> {
    Optional<PengajuanCutiModel> findById(Long idCuti);
    List<PengajuanCutiModel> findAll();
    List<PengajuanCutiModel> findAllByIdstatus(StatusModel stat);
}