    public void init() {

        DatabaseManager m = new DatabaseManager();

        m.main();

        try {
            m.connect(ConnectionDialog.createConnection(defDriver, defURL,
                    defUser, defPassword));
            m.insertTestData();
            m.refreshTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
