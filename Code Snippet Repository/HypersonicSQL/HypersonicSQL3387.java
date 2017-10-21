    private void execSQL(String m, String s, int expect) {

        int retval = SQL_INITIAL;

        try {
            statement.execute(s);

            retval = statement.getUpdateCount();
        } catch (SQLException se) {
            retval = SQL_ABORT;
        }

        assertEquals(m, expect, retval);
    }
