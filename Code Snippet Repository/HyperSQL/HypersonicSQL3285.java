    protected void doClose() {

        try {
            Connection con = getConnection("sa", "password", false);

            if (con != null) {
                Statement stmt = con.createStatement();

                stmt.execute("SHUTDOWN");
                stmt.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
