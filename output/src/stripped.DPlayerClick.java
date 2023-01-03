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
