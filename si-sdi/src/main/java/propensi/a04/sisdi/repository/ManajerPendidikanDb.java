package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.ManajerPendidikanModel;

import java.util.Optional;

@Repository
public interface ManajerPendidikanDb extends JpaRepository<ManajerPendidikanModel,Long> {
    Optional<ManajerPendidikanModel> findByIdkaryawan(KaryawanModel karyawan);
}