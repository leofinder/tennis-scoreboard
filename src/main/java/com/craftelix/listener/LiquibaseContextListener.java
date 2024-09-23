package com.craftelix.listener;

import com.craftelix.util.ConnectionManager;
import com.craftelix.util.PropertiesUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;

@Slf4j
@WebListener
public class LiquibaseContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            try (Connection connection = ConnectionManager.open()) {
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
                    CommandScope update = new CommandScope("update");

                    update.addArgumentValue("changelogFile", PropertiesUtil.get("changeLogFile"));
                    update.addArgumentValue("database", database);

                    update.execute();
                });
            }
            log.info("Liquibase context initialized");
        } catch (Exception e) {
            log.error("FATAL ERROR: error initializing Liquibase", e);
            throw new RuntimeException(e);
        }
    }
}
