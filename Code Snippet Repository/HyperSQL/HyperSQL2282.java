    public void executeFiles(String[] fileStrings)
            throws IOException, SqlToolError, SQLException {
        Map<String, String> sqlVarMap = new HashMap<String, String>();
        sqlVarMap.put("invoker", getClass().getName());
        // This variable is pretty useless, but this should show you how to
        // set variables which you can access inside of scripts like *{this}.

        File file;
        SqlFile sqlFile;
        for (String fileString : fileStrings) {
            file = new File(fileString);
            if (!file.isFile())
                throw new IOException("SQL file not present: "
                        + file.getAbsolutePath());
            sqlFile = new SqlFile(file);
            sqlFile.setConnection(conn);
            sqlFile.addUserVars(sqlVarMap);
            sqlFile.execute();

            // The only reason for the following two statements is so that
            // changes made by one .sql file will effect the future SQL files.
            // Has no effect if you only execute one SQL file.
            conn = sqlFile.getConnection();
            sqlVarMap = sqlFile.getUserVars();
        }
    }
