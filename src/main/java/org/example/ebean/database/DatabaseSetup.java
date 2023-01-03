package org.example.ebean.database;

import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import java.io.File;
import java.util.Arrays;
import org.example.ebean.BukkitEBeanPlugin;

public class DatabaseSetup {

    public static void load() {
        DataSourceConfig dataSourceConfig = configureDataSource();
        DatabaseConfig dbConfig = configureDatabase(dataSourceConfig);

        // We should use the classloader that loaded this plugin
        // because this plugin has our ebean dependencies
        ClassLoader cl = BukkitEBeanPlugin.class.getClassLoader();

        System.out.println(String.join("\n",
            Arrays.stream(cl.getDefinedPackages()).map(Package::getName).sorted(String.CASE_INSENSITIVE_ORDER).toList()));

        DatabaseFactory.createWithContextClassLoader(dbConfig, cl);
        BukkitEBeanPlugin.get().getLogger().info("Successfully created database");
    }

    private static DatabaseConfig configureDatabase(DataSourceConfig dataSourceConfig) {
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setDataSourceConfig(dataSourceConfig);
        dbConfig.setDdlGenerate(true);
        dbConfig.setDdlRun(true);
        return dbConfig;
    }

    private static DataSourceConfig configureDataSource() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(getUrl());

        // There is an error about no username/password
        // Note that sqlite does not use a username/password
        dataSourceConfig.setUsername("whyisthisneeeded");
        dataSourceConfig.setPassword("whyisthisneeeded");

        dataSourceConfig.setPlatform("sqlite");
        return dataSourceConfig;
    }

    private static String getUrl() {
        File databaseFile = BukkitEBeanPlugin.get().getFile("ExampleDb.sqlite");
        return "jdbc:sqlite:" + databaseFile.getAbsolutePath();
    }

}
