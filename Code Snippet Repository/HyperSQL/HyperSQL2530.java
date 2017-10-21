    protected void tearDown() {

        try {
            stmnt.execute("DROP TABLE IF EXISTS link_table");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TestSql.tearDown() error: " + e.getMessage());
        }

        super.tearDown();
    }
