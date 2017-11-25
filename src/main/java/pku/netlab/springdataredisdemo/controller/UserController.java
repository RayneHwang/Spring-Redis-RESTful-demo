package pku.netlab.springdataredisdemo.controller;

import org.springframework.web.bind.annotation.*;
import pku.netlab.springdataredisdemo.model.User;
import pku.netlab.springdataredisdemo.model.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {this.userRepository = userRepository;}

    @PostMapping("/{id}/{name}/{pwd}")
    public User add(@PathVariable("id") final String id,
                    @PathVariable("name") final String name,
                    @PathVariable("pwd") final String pwd) {
        User u = User.builder().name(name).id(id).password(pwd).build();
        userRepository.save(u);
        return u;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") String id) {
        return userRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id")String id){
        try {
            userRepository.delete(id);
            return true;
        }catch (Exception e){
            System.err.println(e);
            return false;
        }
    }
}
