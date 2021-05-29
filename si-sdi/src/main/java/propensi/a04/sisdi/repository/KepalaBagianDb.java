package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.KepalaBagianModel;

import java.util.Optional;

@Repository
public interface KepalaBagianDb extends JpaRepository<KepalaBagianModel,Long> {
    Optional<KepalaBagianModel> findByIdkaryawan(KaryawanModel karyawan);
}