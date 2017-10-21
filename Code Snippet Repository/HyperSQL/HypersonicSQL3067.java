    private static void setup() throws SQLException {

        Connection conn = getConnection();
        Statement  stmt = conn.createStatement();

        stmt.execute(drop_test_table_stmt);
        stmt.execute(create_test_table_stmt);
        stmt.execute(drop_audit_table_stmt);
        stmt.execute(create_audit_table_stmt);
        createTrigger(stmt, "tibr_" + tn, INSERT_BEFORE_ROW);
        createTrigger(stmt, "tia_" + tn, INSERT_AFTER);
        createTrigger(stmt, "tiar_" + tn, INSERT_AFTER_ROW);
        createTrigger(stmt, "tubr_" + tn, UPDATE_BEFORE_ROW);
        createTrigger(stmt, "tua_" + tn, UPDATE_AFTER);
        createTrigger(stmt, "tuar_" + tn, UPDATE_AFTER_ROW);
        createTrigger(stmt, "tdbr_" + tn, DELETE_BEFORE_ROW);
        createTrigger(stmt, "tda_" + tn, DELETE_AFTER);
        createTrigger(stmt, "tdar_" + tn, DELETE_AFTER_ROW);
        stmt.close();
        conn.close();
    }
