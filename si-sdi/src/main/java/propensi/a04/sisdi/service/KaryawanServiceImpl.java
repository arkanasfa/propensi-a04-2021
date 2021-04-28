package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.KaryawanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.KaryawanModel;

import javax.transaction.Transactional;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    private KaryawanDB karyawanDb;

    @Override
    public KaryawanModel getByUser(UserModel user){
        KaryawanModel targetKaryawan = karyawanDb.findByUser(user);
        return targetKaryawan;
    }
}