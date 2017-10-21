    protected void tearDown() {

        System.out.println("TearDown (sub-)test: " + getName());

        try {
            statement = connection.createStatement();

            statement.execute("SHUTDOWN");
            statement.close();

            if (!isNetwork) {
                connection.close();
            }
        } catch (Exception e) {}

        super.tearDown();
    }
