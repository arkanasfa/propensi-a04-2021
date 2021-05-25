package propensi.a04.sisdi.service;


import java.util.List;

import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.UserModel;

public interface KaryawanService {
//    KaryawanModel getByUser(UserModel user);
    KaryawanModel updateSkorPengaduan(KaryawanModel karyawan);
    List<KaryawanModel> getListKaryawan();
}
