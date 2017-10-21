    public void windowClosing(WindowEvent ev) {

        try {
            if (cConn != null) {
                cConn.close();
            }
        } catch (Exception e) {}

        fMain.dispose();

        if (bMustExit) {
            System.exit(0);
        }
    }
