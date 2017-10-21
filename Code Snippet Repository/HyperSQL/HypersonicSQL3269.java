    protected void tearDown() throws Exception {

        // I decided not the use the "IF EXISTS" clause since it is not a
        // SQL standard.
        try {

//            stmt.execute("drop table employee");
            stmt.execute("drop table employee if exists");
        } catch (Exception x) {}

        if (stmt != null) {
            stmt.close();

            stmt = null;
        }

        if (conn != null) {
            conn.close();

            conn = null;
        }

        super.tearDown();

    }
