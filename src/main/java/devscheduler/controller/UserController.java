package devscheduler.controller;

import devscheduler.data.DevUser;
import devscheduler.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public DevUser createUser(@RequestBody DevUser devUser) {
        return userRepository.save(devUser);
    }

    @GetMapping
    public List<DevUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public DevUser getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public DevUser updateUser(@PathVariable Long id, @RequestBody DevUser devUserDetails) {
        DevUser devUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        devUser.setName(devUserDetails.getName());
        devUser.setEmail(devUserDetails.getEmail());
        return userRepository.save(devUser);
    }
}