package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.ManajerSDIModel;

import java.util.Optional;

@Repository
public interface ManajerSDIDb extends JpaRepository<ManajerSDIModel,Long> {
    Optional<ManajerSDIModel> findByIdkaryawan(KaryawanModel karyawan);
}