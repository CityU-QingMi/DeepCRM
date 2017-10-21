    public TestHarness(String url) {

        super("HSQLDB Test Harness");

        this.dbURL = url;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                doClose();
            }
        });
        initComponents();
        setSize(400, 400);
        setLocation(200, 200);
        setVisible(true);

        try {
            Connection c = getConnection("sa", "password", true);

            textArea.setText("Database already exists.");
            c.close();
        } catch (SQLException e1) {
            doCreate();
        }
    }
