    private void checkUnionViews() throws SQLException {

        checkViewTranslationAndContent(
            "U1", null, "SELECT * FROM TABLE_A UNION SELECT * FROM TABLE_B",
            "SELECT PUBLIC.TABLE_A.ID_A,PUBLIC.TABLE_A.NAME_A FROM PUBLIC.TABLE_A UNION SELECT PUBLIC.TABLE_B.ID_B,PUBLIC.TABLE_B.NAME_B FROM PUBLIC.TABLE_B",
            new Object[][] {
            new Object[] {
                new Integer(1), "first A"
            }, new Object[] {
                new Integer(1), "first B"
            }, new Object[] {
                new Integer(2), "second A"
            }, new Object[] {
                new Integer(2), "second B"
            }
        });
        checkViewTranslationAndContent(
            "U2", null,
            "SELECT * FROM ( SELECT * FROM TABLE_A UNION SELECT * FROM TABLE_B )",
            "SELECT ID_A,NAME_A FROM(SELECT PUBLIC.TABLE_A.ID_A,PUBLIC.TABLE_A.NAME_A FROM PUBLIC.TABLE_A UNION SELECT PUBLIC.TABLE_B.ID_B,PUBLIC.TABLE_B.NAME_B FROM PUBLIC.TABLE_B)",
            new Object[][] {
            new Object[] {
                new Integer(1), "first A"
            }, new Object[] {
                new Integer(1), "first B"
            }, new Object[] {
                new Integer(2), "second A"
            }, new Object[] {
                new Integer(2), "second B"
            }
        });
    }
