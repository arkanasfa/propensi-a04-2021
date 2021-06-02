package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.PengaduanModel;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PengaduanService {
    PengaduanModel addPengaduan(PengaduanModel pengaduan);

    List<PengaduanModel> getPengaduanList();

    PengaduanModel getPengaduanById(Long id);

    String generateKodePengaduan(PengaduanModel pengaduan);

    PengaduanModel updatePengaduan(PengaduanModel pengaduan);

    void deletePengaduan(PengaduanModel pengaduan);
    
    Page<PengaduanModel> findPaginated(Pageable pageable, List<PengaduanModel> pengaduan);

}
