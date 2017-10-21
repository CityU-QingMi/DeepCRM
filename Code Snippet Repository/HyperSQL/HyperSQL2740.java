    private void checkInsertions() {

        // check whether inserting a value succeeds
        executeStatement("INSERT INTO \"" + m_products.getName()
                         + "\" VALUES ( 3, 'Pears' )");
        verifyTableContent(m_products.getName(),
                           m_products.appendRowData(new Object[] {
            new Integer(3), "Pears"
        }));

        // check whether the PK constraint works
        try {
            m_statement.execute("INSERT INTO \"" + m_products.getName()
                                + "\" VALUES ( 1, 'Green Apples' )");
            fail("PKs do not work as expected.");
        } catch (SQLException e) {}
    }
