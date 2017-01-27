package resource;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UserRepository;

import java.util.List;

/**
 * Created by mr_St on 27.01.17.
 */
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    public List<User> list() {
        return this.userRepository.findAll();
    }







    //    @GET
//    public List<Member> getAll(){
//        return this.memberDao.findAll();
//    }
}
