    private void setupDatabase() {

        try {
            m_connection = newConnection();
            m_statement  = m_connection.createStatement();

            m_products.createTable(m_connection);
            m_customers.createTable(m_connection);
        } catch (SQLException ex) {
            fail(ex.toString());
        }
    }
