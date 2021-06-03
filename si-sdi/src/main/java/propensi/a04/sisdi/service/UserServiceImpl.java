package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.repository.UserDb;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel findByUUID(String UUID) {
        List<UserModel> users = userDb.findAll();
        for(UserModel user: users){
            if(user.getId().equals(UUID)){
                return user;
            }
        }
        return null;
    }

    @Override
    public UserModel findbyUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public UserModel getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        else {
            username = principal.toString();
        }

        return userDb.findByUsername(username);
    }

    @Override
    public Boolean validatePass(UserModel user){
        String checkPass = user.getPassword();
        System.out.println(checkPass);
        if(checkPass.matches("[a-zA-Z0-9]+") && checkPass.length()>=8){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkConfirmPass(String checkPass, String newPass){
        if(checkPass.equals(newPass)){
            return true;
        }
        return false;
    }


}
