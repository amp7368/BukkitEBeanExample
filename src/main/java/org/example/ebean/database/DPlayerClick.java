package org.example.ebean.database;

import io.ebean.annotation.Identity;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import org.bukkit.event.block.Action;

@Entity
public class DPlayerClick {

    @Id
    @Identity
    private long id;
    @Column
    private final Timestamp timestamp;
    @Column
    private final String playerName;
    @Column
    @Enumerated(EnumType.STRING)
    private final Action action;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    public DPlayerClick(String playerName, Action action) {
        this.timestamp = Timestamp.from(Instant.now());
        this.playerName = playerName;
        this.action = action;
    }

    @Override
    public String toString() {
        String prettyDate = dateFormatter.format(timestamp.toInstant().atZone(ZoneId.of("GMT")));
        return "%s performed %s at %s".formatted(playerName, action, prettyDate);
    }

    public long getId() {
        return id;
    }
}
