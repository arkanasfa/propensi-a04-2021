package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PengaduanModel;
import propensi.a04.sisdi.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface PengaduanService {
    // Method untuk menambah pengaduan
    PengaduanModel addPengaduan(PengaduanModel pengaduan);

    List<PengaduanModel> getPengaduanList();

    //List<PengaduanModel> getPengaduanByUser(UserModel user);

    PengaduanModel updatePengaduan (PengaduanModel pengaduanModel);

    Optional<PengaduanModel> getPengaduanById(Long id);

    String generateKodePengaduan(PengaduanModel pengaduan);
    


}
