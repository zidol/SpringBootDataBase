package me.zidol.springbootjdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class PgSQLRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(PgSQLRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try(Connection connection = dataSource.getConnection()) {
            logger.info(String.valueOf(dataSource.getClass()));
            logger.info(connection.getMetaData().getDriverName());
            logger.info(connection.getMetaData().getURL());
            logger.info(connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE account (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        }

        jdbcTemplate.execute("INSERT into account values(1, 'zidol')");

    }
}
