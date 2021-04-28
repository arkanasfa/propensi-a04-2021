package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.DokumenTotalModel;
import propensi.a04.sisdi.model.GajiModel;
import propensi.a04.sisdi.model.KaryawanModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface GajiDb extends JpaRepository<GajiModel,Long> {
    Optional<GajiModel> findById(Long id);
    List<GajiModel> findByUnitAndDokumen(String unit, DokumenTotalModel dokumen);

}