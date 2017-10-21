    private static void printMeta(Connection p_connection)
    throws SQLException {

        System.out.println("GET METADATA START ...");

        ResultSet rs = p_connection.getMetaData().getTables(null, null, null,
            null);

        System.out.println(rs.toString());

        int col_count = rs.getMetaData().getColumnCount();

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

        System.out.println("END GET METADATA");
    }
