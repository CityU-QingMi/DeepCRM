    @Before
    public void setUp() throws Exception {
        eventSource = new AvroSource();
        channel = new MemoryChannel();

        Configurables.configure(channel, new Context());

        avroLogger = (Logger) LogManager.getLogger("avrologger");
/**/
/**/
/**/
/**/
        removeAppenders(avroLogger);
        final Context context = new Context();
        testPort = String.valueOf(AvailablePortFinder.getNextAvailable());
        context.put("port", testPort);
        context.put("bind", "0.0.0.0");
        Configurables.configure(eventSource, context);

        final List<Channel> channels = new ArrayList<>();
        channels.add(channel);

        final ChannelSelector cs = new ReplicatingChannelSelector();
        cs.setChannels(channels);

        eventSource.setChannelProcessor(new ChannelProcessor(cs));

        eventSource.start();

        Assert.assertTrue("Reached start or error", LifecycleController
                .waitForOneOf(eventSource, LifecycleState.START_OR_ERROR));
        Assert.assertEquals("Server is started", LifecycleState.START,
                eventSource.getLifecycleState());
    }
