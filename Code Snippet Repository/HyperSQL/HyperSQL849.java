    public synchronized boolean execute(
            String sql, String[] columnNames) throws SQLException {

        if (columnNames == null || columnNames.length == 0) {
            throw JDBCUtil.invalidArgument("columnIndexes");
        }
        fetchResult(sql, StatementTypes.RETURN_ANY,
                    ResultConstants.RETURN_GENERATED_KEYS_COL_NAMES, null,
                    columnNames);

        return resultIn.isData();
    }
