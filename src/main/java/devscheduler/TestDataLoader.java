package devscheduler;

import com.opencsv.exceptions.CsvException;
import devscheduler.data.DevUser;
import devscheduler.importer.CsvUtil;
import devscheduler.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class TestDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            ClassPathResource resource = new ClassPathResource("dev-users.csv");
            List<DevUser> users = CsvUtil.readUsersFromCsv(new InputStreamReader(resource.getInputStream()));
            userRepository.saveAll(users);

            // Fetch and print all users
            List<DevUser> allUsers = userRepository.findAll();
            allUsers.forEach(user -> System.out.println("User: " + user.getName() + ", Email: " + user.getEmail() + ", ID: " + user.getId()));
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

}