    private static void doAuditStep(int typ, String tn, String ors,
                                    String nrs) {

        Connection        conn;
        PreparedStatement stmt;

        switch (typ) {

            case INSERT_AFTER_ROW :
            case UPDATE_AFTER_ROW :
            case DELETE_AFTER_ROW : {
                try {
                    conn = getConnection();
                    stmt = conn.prepareStatement(audit_insert_stmt);

                    stmt.setString(1, getOperationSpec(typ));
                    stmt.setString(2, tn);
                    stmt.setString(3, ors);
                    stmt.setString(4, nrs);
                    stmt.executeUpdate();
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }
