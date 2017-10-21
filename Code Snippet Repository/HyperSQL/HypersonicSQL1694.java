    private void performUpdate() throws SQLException {

        preparedStatement.parameterValues[columnCount] =
            getCurrent()[columnCount];

        for (int i = 0; i < columnCount; i++) {
            boolean set = preparedStatement.parameterSet[i] != null;

            preparedStatement.resultOut.metaData.columnTypes[i] = set
                    ? preparedStatement.parameterTypes[i]
                    : Type.SQL_ALL_TYPES;
        }
        preparedStatement.resultOut.setActionType(
            ResultConstants.UPDATE_CURSOR);
        preparedStatement.fetchResult();
        preparedStatement.clearParameters();

        rootWarning = preparedStatement.getWarnings();

        preparedStatement.clearWarnings();

        isRowUpdated = false;
    }
