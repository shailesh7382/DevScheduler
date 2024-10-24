package devscheduler.importer;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import devscheduler.data.DevUser;
import devscheduler.data.TimeOff;

import java.io.Reader;
import java.io.IOException;
import java.time.LocalDate;
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

    public static List<TimeOff> readTimeOffsFromCsv(Reader reader) throws IOException, CsvException {
        List<TimeOff> timeOffs = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(reader)) {
            List<String[]> records = csvReader.readAll();
            boolean isFirstRow = true;
            for (String[] record : records) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Skip the header row
                }
                TimeOff timeOff = new TimeOff();
                timeOff.setStartDate(LocalDate.parse(record[0]));
                timeOff.setEndDate(LocalDate.parse(record[1]));
                DevUser user = new DevUser();
                user.setName(record[2]);
                timeOff.setDevUser(user);
                timeOffs.add(timeOff);
            }
        }
        return timeOffs;
    }
}