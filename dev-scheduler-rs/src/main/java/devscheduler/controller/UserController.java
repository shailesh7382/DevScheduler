package devscheduler.controller;

import devscheduler.data.DevUser;
import devscheduler.data.TimeOff;
import devscheduler.jpa.TimeOffRepository;
import devscheduler.jpa.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management System", description = "Operations related to user management")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeOffRepository timeOffRepository;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<DevUser> createUser(@RequestBody DevUser devUser) {
        logger.info("Creating new user: {}", devUser.getName());
        DevUser savedUser = userRepository.save(devUser);
        logger.info("User created with ID: {}", savedUser.getId());
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    @Operation(summary = "Get a list of all users")
    public ResponseEntity<List<DevUser>> getAllUsers() {
        logger.info("Fetching all users");
        List<DevUser> users = userRepository.findAll();
        logger.info("Number of users fetched: {}", users.size());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<DevUser> getUserById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    logger.info("User found: {}", user.getName());
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> {
                    logger.warn("User not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user by ID")
    public ResponseEntity<DevUser> updateUser(@PathVariable Long id, @RequestBody DevUser devUserDetails) {
        logger.info("Updating user with ID: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(devUserDetails.getName());
                    user.setEmail(devUserDetails.getEmail());
                    DevUser updatedUser = userRepository.save(user);
                    logger.info("User updated: {}", updatedUser.getName());
                    return ResponseEntity.ok(updatedUser);
                })
                .orElseGet(() -> {
                    logger.warn("User not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping("/{id}/timeoffs")
    @Operation(summary = "Add time-off for a user")
    public ResponseEntity<TimeOff> addTimeOff(@PathVariable("id") Long id, @RequestBody TimeOff timeOff) {
        logger.info("Adding time-off for user with ID: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    timeOff.setDevUser(user);
                    TimeOff savedTimeOff = timeOffRepository.save(timeOff);
                    logger.info("Time-off added with ID: {}", savedTimeOff.getId());
                    return ResponseEntity.ok(savedTimeOff);
                })
                .orElseGet(() -> {
                    logger.warn("User not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }
}