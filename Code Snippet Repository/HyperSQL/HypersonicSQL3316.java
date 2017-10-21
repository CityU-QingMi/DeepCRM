    protected void setUp() throws Exception {

        super.setUp();

        try {
            prepareDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
