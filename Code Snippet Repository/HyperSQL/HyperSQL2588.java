    protected void setUp() throws Exception {

        System.out.println("SetUp (sub-)test: " + getName());
        super.setUp();

        try {
            connection = super.newConnection();
            statement  = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
