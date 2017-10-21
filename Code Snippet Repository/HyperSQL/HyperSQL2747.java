    public void tearDown() {

        try {
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        super.tearDown();
    }
