package org.example.ebean.database;

import org.example.ebean.database.query.QDPlayerClick;

public class FindByQueryBean {

    public static DPlayerClick lastClick(String playerName) {
        return new QDPlayerClick().where().playerName.eq(playerName).orderBy().timestamp.desc().setMaxRows(1).findOne();
    }

}
