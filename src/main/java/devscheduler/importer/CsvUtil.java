package devscheduler.importer;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import devscheduler.data.DevUser;

import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<DevUser> readUsersFromCsv(Reader reader) throws IOException, CsvException {
        List<DevUser> users = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(reader)) {
            List<String[]> records = csvReader.readAll();
            boolean isFirstRow = true;
            for (String[] record : records) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Skip the header row
                }
                DevUser user = new DevUser();
                user.setName(record[0]);
                user.setEmail(record[1]);
                users.add(user);
            }
        }
        return users;
    }
}