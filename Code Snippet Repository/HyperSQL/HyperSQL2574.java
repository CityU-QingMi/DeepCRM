    protected void tearDown() {

        try {
            conn1.close();
        } catch (Exception e) {

            //e.printStackTrace();
            System.out.println(this + ".tearDown() error: " + e.getMessage());
        }

        try {
            conn2.close();
        } catch (Exception e) {

            //e.printStackTrace();
            System.out.println(this + ".tearDown() error: " + e.getMessage());
        }

        server.stop();
    }
