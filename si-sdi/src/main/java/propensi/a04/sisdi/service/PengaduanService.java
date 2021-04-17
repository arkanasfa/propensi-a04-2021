package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.PengaduanModel;

import java.util.List;
import java.util.Optional;

public interface PengaduanService {
    // Method untuk menambah pengaduan
    void addPengaduan(PengaduanModel pengaduan);

    List<PengaduanModel> getPengaduanList();

    PengaduanModel getPengaduanByIdKaryaPengadu( Long idKarya_pengadu);

    PengaduanModel getPengaduanByIdOrtuPengadu( Long idOrtu_pengadu);

    PengaduanModel getPengaduanByIdSiswaPengadu( Long idSiswa_pengadu);

    PengaduanModel updatePengaduan (PengaduanModel pengaduanModel);

    Optional<PengaduanModel> getPengaduanById(Long id);
    


}
