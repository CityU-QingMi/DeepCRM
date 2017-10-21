    public synchronized boolean execute(
            String sql, int[] columnIndexes) throws SQLException {

        if (columnIndexes == null || columnIndexes.length == 0) {
            throw JDBCUtil.invalidArgument("columnIndexes");
        }
        fetchResult(sql, StatementTypes.RETURN_ANY,
                    ResultConstants.RETURN_GENERATED_KEYS_COL_INDEXES,
                    columnIndexes, null);

        return resultIn.isData();
    }
