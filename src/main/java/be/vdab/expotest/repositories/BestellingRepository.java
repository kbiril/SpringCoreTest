package be.vdab.expotest.repositories;
import be.vdab.expotest.domain.Bestelling;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class BestellingRepository {
    private final JdbcTemplate template;

    public BestellingRepository(JdbcTemplate template) {
        this.template = template;
    }

    public int create (Bestelling bestelling) {
        var sql = """
                    insert into bestellingen (naam, ticketType)
                    values (?, ?)
                  """;
        var keyHolder = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    statement.setString(1, bestelling.getNaam());
                    statement.setInt(2, bestelling.getTicketType());
                    return statement;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }
}