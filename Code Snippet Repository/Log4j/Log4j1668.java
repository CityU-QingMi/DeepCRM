    @Before
    public void setUp() throws Exception {
                try {
                    this.app = (ListAppender) init.getAppender("List");
                    assertNotNull("No List appender found", app);
                } catch (final Exception ex) {
                    System.out.println("init = " + init == null ? "null" : init);

                }

    }
