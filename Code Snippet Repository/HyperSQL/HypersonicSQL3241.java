    private static void insertData(Connection con) throws SQLException {

        String[] saData = {
            "INSERT INTO XB VALUES('T850','LEAA','00','P',NULL,'LCN NAME','sa',NOW)",
            "INSERT INTO XB VALUES('T850','LEAA01','00','P',NULL,'LCN NAME','sa',NOW)",
            "INSERT INTO XB VALUES('T850','LEAA02','00','P',NULL,'LCN NAME','sa',NOW)",
            "INSERT INTO XB VALUES('T850','LEAA03','00','P',NULL,'LCN NAME','sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA','00','P','ABCDEFG',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA','00','P','QRSTUJV',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA','00','P','ZZZZZZZ',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA01','00','P','ABCDEFG',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA01','00','P','QRSTUJV',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA01','00','P','ZZZZZZZ',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA02','00','P','ABCDEFG',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA02','00','P','QRSTUJV',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA02','00','P','ZZZZZZZ',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA03','00','P','ABCDEFG',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA03','00','P','QRSTUJV',3.14,'sa',NOW)",
            "INSERT INTO CA VALUES('T850','LEAA03','00','P','ZZZZZZZ',3.14,'sa',NOW)"
        };
        Statement stmt = con.createStatement();

        for (int index = 0; index < saData.length; index++) {
            stmt.executeUpdate(saData[index]);
        }
    }    // insertData
