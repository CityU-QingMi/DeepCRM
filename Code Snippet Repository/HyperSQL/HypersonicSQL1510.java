    private void startUpdate() throws SQLException {

        if (originalClob != null) {
            return;
        }

        originalClob = clob;
        clob         = (ClobDataID) clob.duplicate(session);

        resultSet.startUpdate(colIndex + 1);

        resultSet.preparedStatement.parameterValues[colIndex] = clob;
        resultSet.preparedStatement.parameterSet[colIndex]    = Boolean.TRUE;
    }
