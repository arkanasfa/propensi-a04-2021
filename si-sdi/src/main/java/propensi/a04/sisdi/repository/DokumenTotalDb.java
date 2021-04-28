package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.DokumenTotalModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DokumenTotalDb extends JpaRepository<DokumenTotalModel,Long> {
    List<DokumenTotalModel> findAll();
    Optional<DokumenTotalModel> findDokumenTotalByTanggalIsu(Date yearmonth);
}
