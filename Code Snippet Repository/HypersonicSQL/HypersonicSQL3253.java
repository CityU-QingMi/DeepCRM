    protected void setUp() throws Exception {

        super.setUp();

        Connection connection = super.newConnection();
        Statement  statement  = connection.createStatement();

        statement.execute("drop table time_test if exists");
        statement.execute("drop table date_test if exists");
        statement.execute("create table time_test(time_test time)");
        statement.execute("create table date_test(date_test date)");
        connection.close();
    }
