    private static void createDatabase() throws SQLException {

        new File("testdb.backup").delete();
        new File("testdb.data").delete();
        new File("testdb.properties").delete();
        new File("testdb.script").delete();

        Connection con = DriverManager.getConnection("jdbc:hsqldb:testdb",
            "sa", "");
        String[] saDDL = {
            "CREATE CACHED TABLE XB (EIACODXA VARCHAR(10) NOT NULL, LSACONXB VARCHAR(18) NOT NULL, ALTLCNXB VARCHAR(2) NOT NULL, LCNTYPXB VARCHAR(1) NOT NULL, LCNINDXB VARCHAR(1), LCNAMEXB VARCHAR(19), UPDT_BY VARCHAR(32), LST_UPDT TIMESTAMP, CONSTRAINT XPKXB PRIMARY KEY (EIACODXA, LSACONXB, ALTLCNXB, LCNTYPXB));",

//            "CREATE INDEX XIF2XB ON XB (EIACODXA);",
            "CREATE CACHED TABLE CA ( EIACODXA VARCHAR(10) NOT NULL, LSACONXB VARCHAR(18) NOT NULL, ALTLCNXB VARCHAR(2) NOT NULL, LCNTYPXB VARCHAR(1) NOT NULL, TASKCDCA VARCHAR(7) NOT NULL, TSKFRQCA NUMERIC(7,4), UPDT_BY VARCHAR(32), LST_UPDT TIMESTAMP, CONSTRAINT XPKCA PRIMARY KEY (EIACODXA, LSACONXB, ALTLCNXB, LCNTYPXB, TASKCDCA),        CONSTRAINT R_XB_CA FOREIGN KEY (EIACODXA, LSACONXB, ALTLCNXB, LCNTYPXB) REFERENCES XB ON DELETE CASCADE);",

//            "CREATE INDEX XIF26CA ON CA ( EIACODXA, LSACONXB, ALTLCNXB, LCNTYPXB);"
        };
        Statement stmt = con.createStatement();

        for (int index = 0; index < saDDL.length; index++) {
            stmt.executeUpdate(saDDL[index]);
        }

        stmt.execute("SHUTDOWN");
        con.close();
    }    // createDatabase
