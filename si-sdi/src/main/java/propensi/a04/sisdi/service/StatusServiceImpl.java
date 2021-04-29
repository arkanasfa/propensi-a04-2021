package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.StatusModel;
import propensi.a04.sisdi.repository.PengajuanCutiDb;
import propensi.a04.sisdi.repository.StatusDB;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService{
    @Autowired
    StatusDB statusDb;

    @Override
    public StatusModel getStatusById(Long id_status) {
        return statusDb.findById(id_status).get();
    }
}
