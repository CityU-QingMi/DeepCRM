    public static void main(String[] args) {

        if (args.length == 0) {
            args = new String[]{ "testrecovery" };
        }

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver").newInstance();
        } catch (Exception e) {
            System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();

            return;
        }

        new TestHarness("jdbc:hsqldb:file:" + args[0]);
    }
