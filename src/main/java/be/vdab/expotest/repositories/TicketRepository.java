package be.vdab.expotest.repositories;
import be.vdab.expotest.domain.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {
    private final JdbcTemplate template;

    public TicketRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Ticket> ticketRowMapper =
            (result, rowNum) -> new Ticket(
                    result.getInt("juniorDag"),
                    result.getInt("seniorDag")
            );

    public Ticket lockForUpdate () {

        var sql = """
                        select juniorDag, seniorDag
                        from tickets
                        for update
                      """;
        return template.queryForObject(sql, ticketRowMapper);

    }
    public void update (Ticket ticket) {
        var sql = """
                    update tickets
                    set juniorDag = ?, seniorDag = ?
                  """;

        template.update(sql, ticket.getJuniorDag(), ticket.getSeniorDag());
    }
}
