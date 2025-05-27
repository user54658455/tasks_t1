package io.gatling.demo.commonelements;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Feeders {

    /*  //using .eager() because we use .random() selection from .csv (but do not do so with large files)
  private FeederBuilder<String> feederUsers = csv("data/usersCreatedByGatling.csv")
          .eager()
          .random();*/

    public static Iterator<Map<String, Object>> feederUsers =
            Stream.generate((Supplier<Map<String, Object>>) () -> {
                        String username = "";
                        String password = "";
                        String name = "";
                        String lastname = "";
                        String address1 = "";
                        String address2 = "";

                        //generate empty file if not exists
                        //if exists read headers from it, if no headers put empty strings to Map
                        File fileCheck = new File("./src/test/resources/data/usersCreatedByGatling.csv");
                        try {
                            if (fileCheck.createNewFile()) {
                                //empty file created and we do nothing
                            }
                            else {
                                List lines = FileUtils.readLines(new File("./src/test/resources/data/usersCreatedByGatling.csv"));
                                int maxRowNumber = lines.size();
                                int rowRandomNumber = ThreadLocalRandom.current().nextInt(0, maxRowNumber);
                                String rowRandom = lines.get(rowRandomNumber).toString();
                                String[] cells = rowRandom.split(",");
                                username = cells[0];
                                password = cells[1];
                                name = cells[1];
                                lastname = cells[1];
                                address1 = cells[1];
                                address2 = cells[1];
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                        return Map.of("username", username,
                                "password", password,
                                "name", name,
                                "lastname", lastname,
                                "address1", address1,
                                "address2", address2);
                    }
            ).iterator();


    public static Iterator<Map<String, Object>> feederUsersForVolumeTest =
            Stream.generate((Supplier<Map<String, Object>>) () -> {
                        String username = "";
                        String password = "";
                        String name = "";
                        String lastname = "";
                        String address1 = "";
                        String address2 = "";

                        //generate empty file if not exists
                        //if exists read headers from it, if no headers put empty strings to Map
                        File fileCheck = new File("./src/test/resources/data/usersCreatedByGatling.csv");
                        try {
                            if (fileCheck.createNewFile()) {
                                //empty file created and we do nothing
                            }
                            else {
                                List lines = FileUtils.readLines(new File("./src/test/resources/data/usersCreatedByGatling.csv"));
                                String rowForVolumeUser = lines.get(50).toString(); //set number of row manually for every 15 last rows (36-50 inclusively)
                                String[] cells = rowForVolumeUser.split(",");
                                username = cells[0];
                                password = cells[1];
                                name = cells[1];
                                lastname = cells[1];
                                address1 = cells[1];
                                address2 = cells[1];
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                        return Map.of("username", username,
                                "password", password,
                                "name", name,
                                "lastname", lastname,
                                "address1", address1,
                                "address2", address2);
                    }
            ).iterator();


/*    // import org.apache.commons.lang3.RandomStringUtils
    public static Iterator<Map<String, Object>> feederEmail =
            Stream.generate((Supplier<Map<String, Object>>) () -> {
                        String email = RandomStringUtils.randomAlphanumeric(20) + "@foo.com";
                        return Map.of("email", email);
                    }
            ).iterator();*/

    public static Iterator<Map<String, Object>> feederDateDepartArrive =
            Stream.generate((Supplier<Map<String, Object>>) () -> {
                        LocalDate currentDate = LocalDate.now();

                        String currentDateToDepartDatePlus = RandomStringUtils.random(1,49,54,false,true);
                        LocalDate currentDateToDepartDate = currentDate.plusDays(Long.parseLong(currentDateToDepartDatePlus));
                        DateTimeFormatter departDateToFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        String departDateFormated = currentDateToDepartDate.format(departDateToFormat);

                        String currentDateToArriveDatePlus = RandomStringUtils.random(1,54,58,false,true);
                        LocalDate currentDateToArriveDate = currentDate.plusDays(Long.parseLong(currentDateToArriveDatePlus));
                        DateTimeFormatter arriveDateToFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        String arriveDateFormated = currentDateToArriveDate.format(arriveDateToFormat);

                        return Map.of("departDate", departDateFormated,
                                "returnDate", arriveDateFormated);
                    }
            ).iterator();

    public static Iterator<Map<String, Object>> feederCreditCard =
            Stream.generate((Supplier<Map<String, Object>>) () -> {
                        String cardNum = RandomStringUtils.random(16,49,58,false,true);

                        LocalDate currentDate = LocalDate.now();
                        String currentDateToCardExpPlus = RandomStringUtils.random(2,52,54,false,true);
                        LocalDate currentDateToCardExp = currentDate.plusWeeks(Long.parseLong(currentDateToCardExpPlus));
                        DateTimeFormatter CardExpToFormat = DateTimeFormatter.ofPattern("MM/yy");
                        String cardExp = currentDateToCardExp.format(CardExpToFormat);

                        return Map.of("cardNum", cardNum,
                                "cardExp", cardExp);
                    }
            ).iterator();

    public static Iterator<Map<String, Object>> feederNewUserData =
            Stream.generate((Supplier<Map<String, Object>>) () -> {
                        String newUsername = RandomStringUtils.random(6,97,123,true,false);
                        String newPassword = RandomStringUtils.random(6,97,123,true,false);
                        String newFirstName = RandomStringUtils.random(6,97,123,true,false);
                        String newLastName = RandomStringUtils.random(6,97,123,true,false);
                        String newAddress1 = RandomStringUtils.random(6,97,123,true,false);
                        String newAddress2 = RandomStringUtils.random(6,97,123,true,false);

                        return Map.of("newUsername", newUsername,
                                "newPassword", newPassword,
                                "name", newFirstName,
                                "lastname", newLastName,
                                "address1", newAddress1,
                                "address2", newAddress2);
                    }
            ).iterator();

    public static Iterator<Map<String, Object>> feederNewUserDataSaveToCsv =
            Stream.generate((Supplier<Map<String, Object>>) () -> {
                        String newUsername = RandomStringUtils.random(6,97,123,true,false);
                        String newPassword = RandomStringUtils.random(6,97,123,true,false);
                        String newFirstName = RandomStringUtils.random(6,97,123,true,false);
                        String newLastName = RandomStringUtils.random(6,97,123,true,false);
                        String newAddress1 = RandomStringUtils.random(6,97,123,true,false);
                        String newAddress2 = RandomStringUtils.random(6,97,123,true,false);

                        //-----------------------------------------------------------------------------------------------------------------------------------

                        String[] creds = {newUsername, newPassword, newFirstName, newLastName, newAddress1, newAddress2};
                        String[] credsWithHeaders = {"username", "password", "name", "lastname", "address1", "address2"};

                        boolean firstCred = true;
                        StringBuilder stringBuilder = new StringBuilder();
                        String cred = "";

                        File fileCheck = new File("./src/test/resources/data/usersCreatedByGatling.csv");
                        try {
                            if (fileCheck.createNewFile()) {
                                //System.out.println("file created");
                                for (int j = 0; j < credsWithHeaders.length; j++)
                                {
                                    cred = credsWithHeaders[j];
                                    //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! cred is " + cred);

                                    //if the first cred in the line, separator is not needed
                                    if (!firstCred) {
                                        stringBuilder.append(",");
                                    }

                                    stringBuilder.append(cred);
                                    firstCred = false;
                                }
                                stringBuilder.append("\n");
                                firstCred = true;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        String csvFile = "./src/test/resources/data/usersCreatedByGatling.csv";

                        FileWriter fileWriter = null;
                        try {
                            fileWriter = new FileWriter(csvFile, true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        for (int i = 0; i < creds.length; i++)
                        {
                            cred = creds[i];
                            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! cred is " + cred);

                            //if the first cred in the line, separator is not needed
                            if (!firstCred) {
                                stringBuilder.append(",");
                            }

                            stringBuilder.append(cred);
                            firstCred = false;
                        }
                        stringBuilder.append("\n");

                        //add to file the line
                        try {
                            fileWriter.append(stringBuilder.toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        //proper close to file
                        try {
                            fileWriter.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            fileWriter.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        //-----------------------------------------------------------------------------------------------------------------------------------
                        return Map.of("newUsername", newUsername,
                                "newPassword", newPassword,
                                "name", newFirstName,
                                "lastname", newLastName,
                                "address1", newAddress1,
                                "address2", newAddress2);
                    }
            ).iterator();

}