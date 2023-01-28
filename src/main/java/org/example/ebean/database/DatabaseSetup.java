package org.example.ebean.database;

import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import java.io.File;
import org.example.ebean.BukkitEBeanPlugin;

public class DatabaseSetup {

    public static void load() {
        DataSourceConfig dataSourceConfig = configureDataSource();
        DatabaseConfig dbConfig = configureDatabase(dataSourceConfig);

        // We should use the classloader that loaded this plugin
        // because this plugin has our ebean dependencies
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader pluginClassLoader = BukkitEBeanPlugin.class.getClassLoader();

        // create the DatabaseFactory with the classloader containing ebean dependencies
        DatabaseFactory.createWithContextClassLoader(dbConfig, pluginClassLoader);

        // Set the current thread's contextClassLoader to the classLoader with the ebean dependencies
        // This allows the class to initialize itself with access to the required class dependencies
        Thread.currentThread().setContextClassLoader(pluginClassLoader);

        // invoke the static initialization of every class that contains a querybean.
        // Note that any method in the class will initialize the class.
        FindByQueryBean.init();

        // Restore the contextClassLoader to what it was originally
        Thread.currentThread().setContextClassLoader(originalClassLoader);

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
