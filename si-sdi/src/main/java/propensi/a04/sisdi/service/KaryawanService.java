package propensi.a04.sisdi.service;


import java.util.List;
import java.util.Optional;

import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.UserModel;

public interface KaryawanService {
    KaryawanModel getByIduser(UserModel user);
    KaryawanModel updateSkorPengaduan(KaryawanModel karyawan);
    List<KaryawanModel> getListKaryawan();
    KaryawanModel getKaryawanbyId(Long id);
    void addKaryawan(KaryawanModel karyawan);
}
