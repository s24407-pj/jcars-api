package edu.pjatk.jcarsapi;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
public abstract class AbstractTestContainers {

    /*@BeforeAll
    static void beforeAll() {
        Flyway flyway = Flyway.configure().dataSource(
                postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
        ).load();
        flyway.migrate();
    }*/

    @Container
    protected static final MySQLContainer<?> mySQLContainer =
            new MySQLContainer<>("mysql:8.0")
                    .withDatabaseName("jcars-test")
                    .withUsername("admin")
                    .withPassword("password");

    @DynamicPropertySource
    private static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.datasource.url",
                mySQLContainer::getJdbcUrl
        );
        registry.add(
                "spring.datasource.username",
                mySQLContainer::getUsername
        );
        registry.add(
                "spring.datasource.password",
                mySQLContainer::getPassword
        );
    }

    private static DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(mySQLContainer.getDriverClassName())
                .url(mySQLContainer.getJdbcUrl())
                .username(mySQLContainer.getUsername())
                .password(mySQLContainer.getPassword())
                .build();

    }

}
