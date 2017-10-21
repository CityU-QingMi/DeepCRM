    String fixupColumnDefRead(TransferTable t, ResultSetMetaData meta,
                              String columnType, ResultSet columnDesc,
                              int columnIndex) throws SQLException {

        String CompareString = "UNIQUEKEY(\'" + t.Stmts.sDestTable + "\'";

        if (columnType.indexOf(CompareString) > 0) {

            // We just found a increment
            columnType = "SERIAL";
        }

        return (columnType);
    }
