    protected void tearDown() throws Exception {

        // Shut down to destroy all of the DB objects (only works because
        // it's an in-memory instance.
        execSQL("SHUTDOWN", shutdownTested);

        if (con != null) {
            con.close();
        }

        super.tearDown();
    }
