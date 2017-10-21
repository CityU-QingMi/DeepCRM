    private void checkMultipleTables() throws SQLException {

        // ................................................................
        checkViewTranslationAndContent(
            "M1", null, "SELECT * FROM TABLE_A, TABLE_B",
            "SELECT PUBLIC.TABLE_A.ID_A,PUBLIC.TABLE_A.NAME_A,PUBLIC.TABLE_B.ID_B,PUBLIC.TABLE_B.NAME_B FROM PUBLIC.TABLE_A,PUBLIC.TABLE_B",
            new Object[][] {
            new Object[] {
                new Integer(1), "first A", new Integer(1), "first B"
            }, new Object[] {
                new Integer(1), "first A", new Integer(2), "second B"
            }, new Object[] {
                new Integer(2), "second A", new Integer(1), "first B"
            }, new Object[] {
                new Integer(2), "second A", new Integer(2), "second B"
            }
        });

        // ................................................................
        checkViewTranslationAndContent(
            "M2", null, "SELECT TABLE_B.*, TABLE_A.* FROM TABLE_A, TABLE_B",
            "SELECT  PUBLIC.TABLE_B.ID_B,PUBLIC.TABLE_B.NAME_B , PUBLIC.TABLE_A.ID_A,PUBLIC.TABLE_A.NAME_A  FROM PUBLIC.TABLE_A,PUBLIC.TABLE_B",
            new Object[][] {
            new Object[] {
                new Integer(1), "first B", new Integer(1), "first A"
            }, new Object[] {
                new Integer(2), "second B", new Integer(1), "first A"
            }, new Object[] {
                new Integer(1), "first B", new Integer(2), "second A"
            }, new Object[] {
                new Integer(2), "second B", new Integer(2), "second A"
            }
        });

        // ................................................................
        checkViewTranslationAndContent(
            "M3", null, "SELECT \"TABLE_A\".* FROM TABLE_A, TABLE_B",
            "SELECT PUBLIC.TABLE_A.ID_A,PUBLIC.TABLE_A.NAME_A FROM PUBLIC.TABLE_A,PUBLIC.TABLE_B",
            new Object[][] {
            new Object[] {
                new Integer(1), "first A"
            }, new Object[] {
                new Integer(1), "first A"
            }, new Object[] {
                new Integer(2), "second A"
            }, new Object[] {
                new Integer(2), "second A"
            }
        });
    }
