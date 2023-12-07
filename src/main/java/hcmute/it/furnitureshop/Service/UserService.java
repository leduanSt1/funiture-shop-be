package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Auth.RegisterRequest;
import hcmute.it.furnitureshop.DTO.BestUser;
import hcmute.it.furnitureshop.DTO.CreateUserDTO;
import hcmute.it.furnitureshop.DTO.UserDTO;
import hcmute.it.furnitureshop.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAll();
    public Optional<User> findByName(String name);
    public <S extends User> void savePhoneOfUser(User user,String phone);
    public <S extends User> void saveUser(User user);
    public Optional<User> findByPhone(String phone);
    public Optional<User> findById(Integer userId);
    public UserDTO getById(Integer userId);
    public User createUser(CreateUserDTO request);
    public String deleteUser(Integer userId);
    public String updateStatusUser(Integer userId);
    int getTotalNewUser();
    ArrayList<BestUser> getTop3User();
}
