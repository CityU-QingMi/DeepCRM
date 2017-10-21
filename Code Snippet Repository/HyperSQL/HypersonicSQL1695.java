    private void performInsert() throws SQLException {

        checkUpdatable();

        for (int i = 0; i < columnCount; i++) {
            boolean set = preparedStatement.parameterSet[i] != null;

            if (!set) {
                throw JDBCUtil.sqlException(ErrorCode.X_24515);
            }
            preparedStatement.resultOut.metaData.columnTypes[i] =
                preparedStatement.parameterTypes[i];
        }
        preparedStatement.resultOut.setActionType(
            ResultConstants.INSERT_CURSOR);
        preparedStatement.fetchResult();
        preparedStatement.clearParameters();

        rootWarning = preparedStatement.getWarnings();

        preparedStatement.clearWarnings();
    }
