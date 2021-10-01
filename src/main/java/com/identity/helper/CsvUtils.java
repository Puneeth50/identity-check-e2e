package com.identity.helper;

import com.identity.model.VehicleDetails;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class CsvUtils {

    private static final String testDataLoc = "src/test/resources/testdata/";
    private static final String carRegRegex = "([A-Z]{3}\\s?(\\d{3}|\\d{2}|d{1})\\s?[A-Z])|([A-Z]\\s?(\\d{3}|\\d{2}|\\d{1})\\s?[A-Z]{3})|(([A-HK-PRSVWY][A-HJ-PR-Y])\\s?([0][2-9]|[1-9][0-9])\\s?[A-HJ-PR-Z]{3})";


    /**
     * Gets vehicle details from the files in output directory
     *
     * @param dirName
     * @return VehicleDetails List
     */

    public static List<VehicleDetails> getVehicleDetails(String dirName) {

        Reader reader;
        List<VehicleDetails> vehicleDetailsList;
        CsvToBean<VehicleDetails> csvToBean = null;

        File dir = new File(testDataLoc + dirName);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    reader = new BufferedReader(new FileReader(file));
                    csvToBean = new CsvToBeanBuilder<VehicleDetails>(reader)
                            .withType(VehicleDetails.class)
                            .withSeparator(',')
                            .withIgnoreLeadingWhiteSpace(true)
                            .withIgnoreEmptyLine(true)
                            .build();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        vehicleDetailsList = csvToBean.parse();
        return vehicleDetailsList;

    }

    /**
     * Gets vehicle reg from the files in input directory based on matching regex
     *
     * @param dirName
     * @return list
     */
    public static List<String> getVehicleRegs(String dirName) {

        List<String> vehicleList = new ArrayList<>();
        Pattern regex = Pattern.compile(carRegRegex);
        File dir = new File(testDataLoc + dirName);
        File[] files = dir.listFiles();
        StringBuilder builder = new StringBuilder();

        if (files != null) {
            for (File file : files) {
                try (Stream<String> stream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
                    stream.forEach(s -> builder.append(s).append(System.lineSeparator()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        Matcher matcher = regex.matcher(builder.toString());
        while (matcher.find()) {
            if (matcher.group(0) != null) {
                vehicleList.add(matcher.group(0));
            }
        }

        return vehicleList;

    }

}
