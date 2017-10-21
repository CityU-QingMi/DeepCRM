    @Before
    public void setUp() throws Exception {
        logger = Logger.getLogger(LOGGER_NAME);
        assertThat(logger.getLevel(), equalTo(Level.FINE));
        eventAppender = ListAppender.getListAppender("TestAppender");
        flowAppender = ListAppender.getListAppender("FlowAppender");
        stringAppender = ListAppender.getListAppender("StringAppender");
        assertNotNull(eventAppender);
        assertNotNull(flowAppender);
        assertNotNull(stringAppender);
    }
