public class FindByQueryBean {
    // error is trigger on the initialization of this class
    public static DPlayerClick lastClick(String playerName) {
        return new QDPlayerClick().where().playerName.eq(playerName).orderBy().timestamp.desc().setMaxRows(1).findOne();
    }
}
