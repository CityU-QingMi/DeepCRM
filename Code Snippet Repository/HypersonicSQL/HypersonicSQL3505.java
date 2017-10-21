    private void executeStatement(String sql, int expectedVendorCode) {

        try {
            m_statement.execute(sql);
            assertTrue(
                "executing\n  " + sql
                + "\nwas expected to fail, but it didn't", expectedVendorCode
                    == 0);
        } catch (SQLException ex) {
            if (expectedVendorCode == 0) {
                fail(ex.toString());
            }

            assertEquals(
                "executing\n  " + sql
                + "\ndid not result in the expected error", expectedVendorCode, -ex
                    .getErrorCode());
        }
    }
