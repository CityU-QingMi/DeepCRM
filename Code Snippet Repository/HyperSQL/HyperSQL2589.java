    public void testClobH() {

        System.out.println("Starting (sub-)test: " + getName());

        try {
            String ddl1 = "drop procedure PUBLIC.PROC_H if exists";

            statement.execute(ddl1);

            ddl1 = "create procedure PUBLIC.PROC_H(out p1 clob, out p2 int) READS SQL DATA BEGIN ATOMIC SET p1 = 'dafsdfasdfaefafeajfiwejifpjajsidojfakmvkamsdjfadpsjfoajsdifjaos'; SET p2 = 0; end";

            String dml0 = "call PUBLIC.PROC_H(?, ?)";

            statement.execute(ddl1);

            CallableStatement ps = connection.prepareCall(dml0);

            ps.registerOutParameter(1, java.sql.Types.CLOB);
            ps.registerOutParameter(2, java.sql.Types.INTEGER);
            ps.execute();

            String string = ps.getClob(1).getSubString(1, 10);

            System.out.println(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
