    public void testTriggerAction() {

        runScript();

        try {
            runStatements();
        } catch (SQLException e) {
            e.printStackTrace();
            assertTrue(false);
        }

        try {
            shutdownDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            assertTrue(false);
        }

        try {
            openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            assertTrue(false);
        }

        try {
            runStatements();
        } catch (SQLException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
