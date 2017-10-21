    private void ensureTableColumns(String tableName,
                                    String[] columnNames) throws SQLException {

        ResultSet res = m_connection.getMetaData().getColumns(null, null,
            tableName, "%");

        while (res.next()) {
            assertEquals(
                "unexpected column name in table \"" + tableName
                + "\" at position "
                + (res.getRow() - 1), res.getString(
                    "COLUMN_NAME"), columnNames[res.getRow() - 1]);
        }

        res.previous();
        assertEquals("not enough columns in table \"" + tableName + "\"",
                     columnNames.length, res.getRow());
    }
