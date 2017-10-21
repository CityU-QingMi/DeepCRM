    String fixupColumnDefWrite(TransferTable t, ResultSetMetaData meta,
                               String columnType, ResultSet columnDesc,
                               int columnIndex) throws SQLException {

        if (columnType.equals("SERIAL")) {
            columnType = "INTEGER DEFAULT UNIQUEKEY (\'"
                         + t.Stmts.sSourceTable + "\')";
        }

        return (columnType);
    }
