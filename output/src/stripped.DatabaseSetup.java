import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;

public class DatabaseSetup {

    public static void load() {
        DataSourceConfig dataSourceConfig = configureDataSource();
        DatabaseConfig dbConfig = configureDatabase(dataSourceConfig);

        // We should use the classloader that loaded this plugin
        // because this plugin has our ebean dependencies
        ClassLoader cl = BukkitEBeanPlugin.class.getClassLoader();

        DatabaseFactory.createWithContextClassLoader(dbConfig, cl);
        BukkitEBeanPlugin.get().getLogger().info("Successfully created database");
    }
}
