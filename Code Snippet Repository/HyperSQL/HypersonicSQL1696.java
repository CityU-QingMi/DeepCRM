    private void performDelete() throws SQLException {

        checkUpdatable();

        preparedStatement.parameterValues[columnCount] =
            getCurrent()[columnCount];
        preparedStatement.resultOut.metaData.columnTypes[columnCount] =
            resultMetaData.columnTypes[columnCount];

        preparedStatement.resultOut.setActionType(
            ResultConstants.DELETE_CURSOR);
        preparedStatement.fetchResult();
        preparedStatement.clearParameters();

        rootWarning = preparedStatement.getWarnings();

        preparedStatement.clearWarnings();
    }
