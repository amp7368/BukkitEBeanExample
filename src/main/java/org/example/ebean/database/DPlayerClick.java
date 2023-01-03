package org.example.ebean.database;

import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.bukkit.event.block.Action;

@Entity
public class DPlayerClick {

    @Column
    private final Timestamp timestamp;
    @Column
    private final String playerName;
    @Column
    @Enumerated(EnumType.STRING)
    private final Action action;

    public DPlayerClick(String playerName, Action action) {
        this.timestamp = Timestamp.from(Instant.now());
        this.playerName = playerName;
        this.action = action;
    }
}
