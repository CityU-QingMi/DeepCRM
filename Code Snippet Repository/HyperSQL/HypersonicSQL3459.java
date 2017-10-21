        private void createTable(java.sql.Connection connection)
        throws SQLException {

            String createTable = "DROP TABLE \"" + m_name + "\" IF EXISTS;";

            createTable += "CREATE TEXT TABLE \"" + m_name + "\" ( "
                           + m_columnSpec + " );";

            connection.createStatement().execute(createTable);

            boolean test = isReadOnly(m_name);
            String setTableSource = "SET TABLE \"" + m_name + "\" SOURCE \""
                                    + getDataSourceSpec() + "\"";

            connection.createStatement().execute(setTableSource);
        }
