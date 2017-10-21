    private static void printTable(Connection p_connection,
                                   String p_table) throws SQLException {

        System.out.println("GET TABLE " + p_table + " START ...");

        Statement st        = p_connection.createStatement();
        ResultSet rs        = st.executeQuery("SELECT * FROM " + p_table);
        int       col_count = rs.getMetaData().getColumnCount();

        for (int i = 1; i <= col_count; i++) {
            System.out.print(rs.getMetaData().getColumnLabel(i) + "\t");
        }

        System.out.println("");

        while (rs.next()) {
            for (int i = 1; i <= col_count; i++) {
                System.out.print(rs.getObject(i));
                System.out.print("\t");
            }

            System.out.println("");
        }

        st.close();
        System.out.println("... END GET TABLE " + p_table);
    }
