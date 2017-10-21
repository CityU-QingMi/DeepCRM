    private void verifyTableContent(String tableName,
                                    Object[][] expectedValues) {

        String selectStmt = "SELECT * FROM \"" + tableName + "\" ORDER BY ID";

        try {
            java.sql.ResultSet results = m_statement.executeQuery(selectStmt);
            int                row     = 0;

            while (results.next()) {
                row = results.getRow();

                Object[] expectedRowContent = expectedValues[row - 1];

                for (int col = 0; col < expectedRowContent.length; ++col) {
                    Object expectedValue = expectedRowContent[col];
                    Object foundValue    = results.getObject(col + 1);

                    assertEquals("table " + tableName + ", row " + row
                                 + ", column " + col + ":", expectedValue,
                                     foundValue);
                }
            }

            // finally ensure that there are not more rows in the table than expected
            assertEquals("table " + tableName + "'s row count: ",
                         expectedValues.length, row);
        } catch (junit.framework.AssertionFailedError e) {
            throw e;
        } catch (Throwable t) {
            fail("verifyTableContent(" + tableName + ") failed with "
                 + t.toString());
        }
    }
