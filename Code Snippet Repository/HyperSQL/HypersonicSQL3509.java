    private void ensureTableContent(String tableName,
                                    Object[][] tableData) throws SQLException {

        ResultSet lhs = m_statement.executeQuery("SELECT * FROM \""
            + tableName + "\"");
        ResultSetMetaData meta     = lhs.getMetaData();
        int               colCount = meta.getColumnCount();

        while (lhs.next()) {
            int row = lhs.getRow();

            assertEquals(colCount, tableData[row - 1].length);

            for (int col = 1; col <= colCount; ++col) {
                assertEquals(
                    "unexpected table content in " + tableName + " (row "
                    + row + ", col " + col + ")", tableData[row - 1][col - 1],
                                                  lhs.getObject(col));
            }
        }
    }
