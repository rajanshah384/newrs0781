package com.retailshop.demo.utils;

import com.retailshop.demo.model.Tool;
import com.retailshop.demo.model.ToolsCharge;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FileUtils {
    private static final Logger log = Logger.getLogger(FileUtils.class.getName());

    public static final String RESOURCES_DB_TOOLS_TXT = "db/TOOLS.txt";
    public static final String RESOURCES_DB_TOOLS_CHARGE_TXT = "db/TOOLS_CHARGE.txt";
    public static final String DELIMITER = ",";

    // loads the data from the file database, in our demo
    public static Map<String, Tool> loadToolsData() {
        Map<String, Tool> toolsMap = new HashMap<>();

        try {
            List<String> list = org.apache.commons.io.FileUtils.readLines(new File(FileUtils.class.getClassLoader().getResource(RESOURCES_DB_TOOLS_TXT).getPath()), StandardCharsets.UTF_8);
            list.remove(0);//deleting header
            for (String line : list) {
                String[] data = line.split(DELIMITER);
                toolsMap.put(data[0], new Tool(data[0], data[1], data[2]));
            }

        } catch (Exception ex) {
            log.info("Exception Occurred while loading Data." + ex);
        }

        return toolsMap;
    }

    public static Map<String, ToolsCharge> loadToolsChargeData() {
        Map<String, ToolsCharge> toolsChargeMap = new HashMap<>();
        try {
            List<String> list = org.apache.commons.io.FileUtils.readLines(new File(FileUtils.class.getClassLoader().getResource(RESOURCES_DB_TOOLS_CHARGE_TXT).getPath()), StandardCharsets.UTF_8);
            list.remove(0);//deleting header
            for (String line : list) {
                String[] data = line.split(DELIMITER);
                toolsChargeMap.put(data[0], new ToolsCharge(data[0], Double.valueOf(data[1]), booleanValue(data[2]), booleanValue(data[3]), booleanValue(data[4])));
            }
        } catch (Exception ex) {
            log.info("Exception Occurred while loading Data." + ex);
        }
        return toolsChargeMap;
    }

    private static Boolean booleanValue(String data) {
        return "Yes".equalsIgnoreCase(data);
    }

}
