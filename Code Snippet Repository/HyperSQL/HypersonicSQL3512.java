    private void checkAsterisksCombined() throws SQLException {

        // ................................................................
        checkViewTranslationAndContent(
            "C1", null, "SELECT * AS \"a2\" FROM ABC",
            "SELECT PUBLIC.ABC.ID,PUBLIC.ABC.A,PUBLIC.ABC.B,PUBLIC.ABC.C AS \"a2\" FROM PUBLIC.ABC",
            new Object[][] {
            new Object[] {
                new Integer(1), "a", "b", "c"
            }, new Object[] {
                new Integer(2), "d", "e", "f"
            }
        });

        // ................................................................
        checkViewTranslationAndContent(
            "C2", null, "SELECT B AS \"b2\", ABC.* FROM ABC",
            "SELECT B AS \"b2\", PUBLIC.ABC.ID,PUBLIC.ABC.A,PUBLIC.ABC.B,PUBLIC.ABC.C  FROM PUBLIC.ABC",
            new Object[][] {
            new Object[] {
                "b", new Integer(1), "a", "b", "c"
            }, new Object[] {
                "e", new Integer(2), "d", "e", "f"
            }
        });
    }
