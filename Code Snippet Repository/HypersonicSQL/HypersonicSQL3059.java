    private static void dumpTable(String tn) throws SQLException {

        Connection        conn  = getConnection();
        Statement         stmt  = conn.createStatement();
        ResultSet         rs    = stmt.executeQuery("select * from " + tn);
        ResultSetMetaData rsmd  = rs.getMetaData();
        int               count = rsmd.getColumnCount();

        out.println();
        out.println("****************************************");
        out.println("DUMP FOR TABLE: " + tn);
        out.println("****************************************");
        out.flush();

        while (rs.next()) {
            out.print("[");

            for (int i = 1; i <= count; i++) {
                out.print(rs.getString(i));

                if (i < count) {
                    out.print(" : ");
                }
            }

            out.println("]");
        }

        out.println();
        out.flush();
        rs.close();
        stmt.close();
        conn.close();
    }
