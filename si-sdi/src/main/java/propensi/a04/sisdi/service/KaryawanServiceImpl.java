
package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.LemburModel;
import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.KaryawanModel;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    private KaryawanDb karyawanDb;

//    @Override
//    public KaryawanModel getById_user(UserModel user){
//        KaryawanModel targetKaryawan = karyawanDb.findById_user(user);
//         return targetKaryawan;
//    }

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

    @Override
    public KaryawanModel getKaryawanbyId(Long id) {return karyawanDb.findById(id).get();}

    @Override
    public void addKaryawan(KaryawanModel karyawan){karyawanDb.save(karyawan);}
}

