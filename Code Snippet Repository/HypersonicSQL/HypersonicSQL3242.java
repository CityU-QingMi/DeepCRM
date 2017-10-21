    protected void tearDown() {

        try {
            statement = connection.createStatement();

            statement.execute("SHUTDOWN");
        } catch (Exception e) {}

        super.tearDown();
    }
