package jsonplaceholder.framework.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.nio.file.Paths;
import java.util.Scanner;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtils {

    private static String pathToJSONFiles;
    private static String pathToAllureResults;

    static {
        try {
            pathToJSONFiles = Paths.get("src\\test\\resources\\samplesJSON").toFile().getCanonicalPath();
            pathToAllureResults = Paths.get("test-reports\\allure-results").toFile().getCanonicalPath();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Step("Reading JSON file...")
    public static String readJSONFile(String fileName) {
        String jsonObjectTree = "";
        File directory = new File(pathToJSONFiles);
        File[] directoryListing = directory.listFiles();
        if (directoryListing != null) {
            for (File nested : directoryListing) {
                if (nested.getName().contains(fileName)) {
                    try (FileReader reader = new FileReader(nested)) {
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(reader);
                        jsonObjectTree = String.valueOf(jsonObject);
                    } catch (IOException | ParseException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        } else {
            Assert.fail("Directory does not exists.");
        }
        return jsonObjectTree;
    }

    public static String readFile(String pathToFile){
        try (Scanner scanner = new Scanner(Paths.get(pathToFile), StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

        @Step("Writing to JSON file...")
    public static void writeToJSONFile(String fileName, Map<Object, Object> keyValues) {
        File directory = new File(pathToJSONFiles);
        File[] directoryListing = directory.listFiles();
        if (directoryListing != null) {
            for (File nested : directoryListing) {
                if (nested.getName().contains(fileName)) {
                    try (FileWriter writer = new FileWriter(nested)) {
                        JSONObject tempObject = new JSONObject(keyValues);
                        writer.write(tempObject.toString());
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        }
        else {
            Assert.fail("Directory does not exists.");
        }
    }

    public static void removeAllureResultFileContainingString(String parasiticString) {
        File dir = new File(pathToAllureResults);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.getName().contains("result")) {
                    boolean jsonObjectTreeContainsString = readFile(String.valueOf(child)).contains(parasiticString);
                    if (jsonObjectTreeContainsString) {
                        deleteFile(String.valueOf(child));
                    }
                }
            }
        } else {
            Assert.fail("Directory does not exists.");
        }
    }

    public static void deleteFile(String pathToFile) {
        File file = new File(pathToFile);
        try {
            file.delete();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}