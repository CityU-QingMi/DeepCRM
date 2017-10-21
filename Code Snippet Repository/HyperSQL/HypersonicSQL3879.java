    private void insertTestData() {

        try {
            DatabaseManagerCommon.createTestTables(sStatement);
            txtCommand.setText(
                DatabaseManagerCommon.createTestData(sStatement));

            for (int i = 0; i < DatabaseManagerCommon.testDataSql.length;
                    i++) {
                addToRecent(DatabaseManagerCommon.testDataSql[i]);
            }

            executeCurrentSQL();
        } catch (SQLException e) {

            //  Added: (weconsultants@users)
            CommonSwing.errorMessage(e);
        }
    }
