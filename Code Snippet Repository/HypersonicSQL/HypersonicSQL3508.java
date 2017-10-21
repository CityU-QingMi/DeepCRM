    private void ensureEqualContent(String tableNameLHS,
                                    String tableNameRHS) throws SQLException {

        ResultSet lhs = m_statement.executeQuery("SELECT * FROM \""
            + tableNameLHS + "\"");
        ResultSet rhs = m_statement.executeQuery("SELECT * FROM \""
            + tableNameRHS + "\"");
        ResultSetMetaData meta = lhs.getMetaData();

        while (lhs.next() && rhs.next()) {
            for (int col = 1; col <= meta.getColumnCount(); ++col) {
                assertEquals("table content does not match: cp. "
                             + tableNameLHS + "-" + tableNameRHS + ", row "
                             + lhs.getRow() + ", col "
                             + col, lhs.getObject(col), rhs.getObject(col));
            }
        }

        // lhs should be after last, rhs still on last
        assertTrue("row count does not match: " + tableNameLHS + "-"
                   + tableNameRHS, lhs.isAfterLast() && rhs.isLast());
    }
