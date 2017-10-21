    protected void tearDown() {

        try {
            stmnt.execute("DROP SCHEMA SA IF EXISTS CASCADE;");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TestSql.tearDown() error: " + e.getMessage());
        }

        super.tearDown();
    }
