package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KomponenPengaliModel;

import java.util.Optional;

@Repository
public interface KomponenPengaliDb extends JpaRepository<KomponenPengaliModel,Long> {
    Optional<KomponenPengaliModel> findById(Long id);
}
