    @Before
    public void setUp() throws Exception {
        simpleValidator = new SimpleValidator();
        wiser = new Wiser(2500);
        wiser.getServer()
                .setAuthenticationHandlerFactory(new PlainAuthenticationHandlerFactory(
                        simpleValidator));
        wiser.start();
        
        // set up scheduler
        jobListener = new MyJobListener();
        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.getListenerManager().addJobListener(jobListener);
    }
