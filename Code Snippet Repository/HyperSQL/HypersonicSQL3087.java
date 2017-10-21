    public void writeRow(Session session, Row row,
                         Table table) throws IOException {

        schemaToLog = table.getName().schema;

        writeSessionIdAndSchema(session);
        rowOut.reset();
        rowOut.setMode(RowOutputTextLog.MODE_INSERT);
        rowOut.write(BYTES_INSERT_INTO);
        rowOut.writeString(table.getName().statementName);
        rowOut.write(BYTES_VALUES);
        rowOut.writeData(row, table.getColumnTypes());
        rowOut.write(BYTES_TERM);
        rowOut.write(BYTES_LINE_SEP);
        writeRowOutToFile();
    }
