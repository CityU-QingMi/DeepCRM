    public void writeDeleteStatement(Session session, Table table,
                                     Object[] data) throws IOException {

        schemaToLog = table.getName().schema;

        writeSessionIdAndSchema(session);
        rowOut.reset();
        rowOut.setMode(RowOutputTextLog.MODE_DELETE);
        rowOut.write(BYTES_DELETE_FROM);
        rowOut.writeString(table.getName().statementName);
        rowOut.write(BYTES_WHERE);
        rowOut.writeData(table.getColumnCount(), table.getColumnTypes(), data,
                         table.columnList, table.getPrimaryKey());
        rowOut.write(BYTES_LINE_SEP);
        writeRowOutToFile();
    }
