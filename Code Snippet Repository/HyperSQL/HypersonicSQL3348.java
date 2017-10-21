    protected void tearDown() {

        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TestSql.tearDown() error: " + e.getMessage());
        }
        super.tearDown();
    }
