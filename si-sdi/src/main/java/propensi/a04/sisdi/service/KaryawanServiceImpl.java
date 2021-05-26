
package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.KaryawanModel;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    private KaryawanDb karyawanDb;

    //@Override
    //public KaryawanModel getByUser(UserModel user){
    //    KaryawanModel targetKaryawan = karyawanDb.findByUser(user);
    //     return targetKaryawan;
    //}

    @Override
    public KaryawanModel updateSkorPengaduan(KaryawanModel karyawan) {
        KaryawanModel karyawanTarget = karyawanDb.findById(karyawan.getId()).get();
        try {
            karyawanDb.save(karyawanTarget);
            return karyawanTarget;
        }
        catch(NullPointerException nullException){
            return null;
        }
    }

    @Override
    public List<KaryawanModel> getListKaryawan() {
        return karyawanDb.findAll();
    }
}

