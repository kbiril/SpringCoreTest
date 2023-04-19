package be.vdab.expotest.repositories;
import static org.assertj.core.api.Assertions.*;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import java.sql.SQLException;

@JdbcTest
class DataSourceTest {
    private final DataSource dataSource;
    DataSourceTest (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Test
    void getConnection() throws SQLException {
        try (var connection = dataSource.getConnection())
        {
            assertThat(connection.getCatalog()).isEqualTo("expo");
        }
    }
}