    private void execSQL(String s, boolean ignoreError) throws SQLException {

        try {
            statement.execute(s);
            statement.getUpdateCount();
        } catch (SQLException se) {
            if (!ignoreError) {
                throw se;
            }

//else System.err.println("FAILURE of (" + s + ')');
        }
    }
