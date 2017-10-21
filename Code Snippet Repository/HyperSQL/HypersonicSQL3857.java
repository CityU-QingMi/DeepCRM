    public static void threadedDBM() {

        System.getProperties().put("sun.java2d.noddraw", "true");

        String  urlid        = null;
        String  rcFile       = null;
        boolean autoConnect  = false;
        boolean urlidConnect = false;

        bMustExit = false;

        DatabaseManager m = new DatabaseManager();

        m.main();

        Connection c = null;

        try {
            c = ConnectionDialog.createConnection(m.fMain, "Connect");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (c == null) {
            return;
        }

        m.connect(c);
    }
