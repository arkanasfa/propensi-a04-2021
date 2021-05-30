package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.WakilPengurusHarianModel;

import java.util.Optional;

@Repository
public interface WakilPengurusHarianDb extends JpaRepository<WakilPengurusHarianModel,Long> {
    Optional<WakilPengurusHarianModel> findByIdkaryawan(KaryawanModel karyawan);
}