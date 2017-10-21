    public void testX1() {

        String tableDDL =
            "create table lo_attribute ( "
            + "learningid varchar(15) not null, "
            + "ordering integer not null,"
            + "attribute_value_data varchar(85),"
            + "constraint PK_LO_ATTR primary key (learningid, ordering))";

        try {
            Statement stmt = connection.createStatement();

            stmt.execute("drop table lo_attribute if exists");
            stmt.execute(tableDDL);
            stmt.execute(
                "insert into lo_attribute values('abcd', 10, 'cdef')");
            stmt.execute(
                "insert into lo_attribute values('bcde', 20, 'cdef')");
        } catch (SQLException e) {
            assertEquals(0, 1);
        }

        try {
            String prepared =
                "update lo_attribute set "
                + " ordering = (ordering - 1) where ordering > ?";
            PreparedStatement ps = connection.prepareStatement(prepared);

            ps.setInt(1, 10);
            ps.execute();
        } catch (SQLException e) {
            assertEquals(0, 1);
        }

        try {
            connection.setAutoCommit(false);

            java.sql.Savepoint savepoint =
                connection.setSavepoint("savepoint");

            connection.createStatement().executeQuery("CALL true;");
            connection.rollback(savepoint);
        } catch (SQLException e) {
            assertEquals(0, 1);
        }
    }
