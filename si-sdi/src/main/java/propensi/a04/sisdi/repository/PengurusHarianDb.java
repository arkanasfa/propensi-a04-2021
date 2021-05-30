package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.ManajerSDIModel;
import propensi.a04.sisdi.model.PengurusHarianModel;

import java.util.Optional;

@Repository
public interface PengurusHarianDb extends JpaRepository<PengurusHarianModel,Long> {
    Optional<PengurusHarianModel> findByIdkaryawan(KaryawanModel karyawan);
}