    public void setUp() throws Exception {

        super.setUp();

        try {
            openConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
