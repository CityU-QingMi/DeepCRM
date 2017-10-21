    private void checkSubSelects() throws SQLException {

        // ................................................................
        checkViewTranslationAndContent(
            "Q1", null, "SELECT * FROM ( SELECT * FROM ABC )",
            "SELECT ID,A,B,C FROM(SELECT PUBLIC.ABC.ID,PUBLIC.ABC.A,PUBLIC.ABC.B,PUBLIC.ABC.C FROM PUBLIC.ABC)",
            null);

        // ................................................................
        checkViewTranslationAndContent(
            "Q2", null,
            "SELECT * FROM ( SELECT * FROM TABLE_A ), ( SELECT * FROM TABLE_B )",
            "SELECT ID_A,NAME_A,ID_B,NAME_B FROM(SELECT PUBLIC.TABLE_A.ID_A,PUBLIC.TABLE_A.NAME_A FROM PUBLIC.TABLE_A),(SELECT PUBLIC.TABLE_B.ID_B,PUBLIC.TABLE_B.NAME_B FROM PUBLIC.TABLE_B)",
            null);

        // ................................................................
        checkViewTranslationAndContent(
            "Q3", null, "SELECT A.* FROM ( SELECT * FROM TABLE_A ) AS A",
            "SELECT  A.ID_A,A.NAME_A  FROM(SELECT PUBLIC.TABLE_A.ID_A,PUBLIC.TABLE_A.NAME_A FROM PUBLIC.TABLE_A)AS A",
            null);

        // ................................................................
        checkViewTranslationAndContent(
            "Q4", null,
            "SELECT A.*, B.* FROM ( SELECT * FROM TABLE_A ) AS A, ( SELECT * FROM TABLE_B ) AS B",
            "SELECT  A.ID_A,A.NAME_A , B.ID_B,B.NAME_B  FROM(SELECT PUBLIC.TABLE_A.ID_A,PUBLIC.TABLE_A.NAME_A FROM PUBLIC.TABLE_A)AS A,(SELECT PUBLIC.TABLE_B.ID_B,PUBLIC.TABLE_B.NAME_B FROM PUBLIC.TABLE_B)AS B",
            null);
    }
