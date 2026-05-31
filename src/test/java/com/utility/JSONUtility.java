package com.utility;

import java.io.File;
import java.io.FileReader;

import com.constants.Environments;
import com.google.gson.Gson;
import com.ui.pojos.Config;
import com.ui.pojos.Environment;

public class JSONUtility {

    public static Environment readJSON(Environments env) throws Exception {
        Gson gson = new Gson();
        File jsonFile = new File(System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.json");

        try (FileReader fileReader = new FileReader(jsonFile)) {
            Config config = gson.fromJson(fileReader, Config.class);
            if (config == null || config.getEnvironments() == null) {
                throw new Exception("Invalid JSON config structure: missing environments object");
            }

            Environment environment = config.getEnvironments().get(env.name());
            if (environment == null || environment.getUrl() == null || environment.getUrl().isEmpty()) {
                throw new Exception("URL not defined for environment: " + env.name());
            }

            return environment;
        } catch (Exception e) {
            throw new Exception("Failed to read JSON config file: " + jsonFile.getAbsolutePath(), e);
        }
    }
}
