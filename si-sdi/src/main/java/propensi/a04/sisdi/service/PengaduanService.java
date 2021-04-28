package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.PengaduanModel;

import java.util.List;

public interface PengaduanService {
    PengaduanModel addPengaduan(PengaduanModel pengaduan);

    List<PengaduanModel> getPengaduanList();

    PengaduanModel getPengaduanById(Long id);

    String generateKodePengaduan(PengaduanModel pengaduan);
    


}
