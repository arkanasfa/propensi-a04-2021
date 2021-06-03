package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    UserModel findByUUID(String UUID);
    UserModel findbyUsername(String username);
    UserModel getCurrentUser();
}
