package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.KomponenPengaliModel;

public interface PayrollService {
    KomponenPengaliModel getKomponenGajiById(Long komponenGajiId);
    KomponenPengaliModel ubahKomponenGaji(KomponenPengaliModel komponenGaji);
}
