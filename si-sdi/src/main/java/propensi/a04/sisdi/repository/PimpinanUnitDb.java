package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PimpinanUnitModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface PimpinanUnitDb extends JpaRepository<PimpinanUnitModel,Long> {
    Optional<PimpinanUnitModel> findByIdkaryawan(KaryawanModel karyawan);
}