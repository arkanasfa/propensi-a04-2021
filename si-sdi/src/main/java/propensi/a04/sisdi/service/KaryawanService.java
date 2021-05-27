package propensi.a04.sisdi.service;


import java.util.List;
import java.util.Optional;

import propensi.a04.sisdi.model.KaryawanModel;

public interface KaryawanService {
//    KaryawanModel getByUser(UserModel user);
    KaryawanModel updateSkorPengaduan(KaryawanModel karyawan);
    List<KaryawanModel> getListKaryawan();
    KaryawanModel getKaryawanbyId(Long id);
    void addKaryawan(KaryawanModel karyawan);
}
