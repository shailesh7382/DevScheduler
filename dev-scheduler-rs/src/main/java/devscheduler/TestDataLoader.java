package devscheduler;

import com.opencsv.exceptions.CsvException;
import devscheduler.data.DevUser;
import devscheduler.data.TimeOff;
import devscheduler.importer.CsvUtil;
import devscheduler.jpa.TimeOffRepository;
import devscheduler.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class TestDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeOffRepository timeOffRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            ClassPathResource resource = new ClassPathResource("dev-users.csv");
            List<DevUser> users = CsvUtil.readUsersFromCsv(new InputStreamReader(resource.getInputStream()));
            for (DevUser user : users) {
                Optional<DevUser> existingUser = userRepository.findByName(user.getName());
                if (existingUser.isPresent()) {
                    DevUser userToUpdate = existingUser.get();
                    userToUpdate.setEmail(user.getEmail());
                    userRepository.save(userToUpdate);
                } else {
                    userRepository.save(user);
                }
            }

            // Load TimeOff data (example)
            ClassPathResource timeOffResource = new ClassPathResource("time-off.csv");
            List<TimeOff> timeOffs = CsvUtil.readTimeOffsFromCsv(new InputStreamReader(timeOffResource.getInputStream()));
            for (TimeOff timeOff : timeOffs) {
                Optional<DevUser> user = userRepository.findByName(timeOff.getDevUser().getName());
                user.ifPresent(timeOff::setDevUser);
                timeOffRepository.save(timeOff);
            }

            // Fetch and print all users and their time-offs
            List<DevUser> allUsers = userRepository.findAll();
            allUsers.forEach(user -> {
                System.out.println("User: " + user.getName() + ", Email: " + user.getEmail() + ", ID: " + user.getId());
                timeOffRepository.findAll().forEach(timeOff -> {
                    if (timeOff.getDevUser().getId().equals(user.getId())) {
                        System.out.println("TimeOff: " + timeOff.getStartDate() + " to " + timeOff.getEndDate());
                    }
                });
            });
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}