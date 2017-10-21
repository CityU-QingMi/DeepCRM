	@Before
	public void setUp() throws Exception {
		logger.debug("Setting up before '" + this.testName.getMethodName() + "'");
		this.port = SocketUtils.findAvailableTcpPort(61613);
		this.responseChannel = new ExecutorSubscribableChannel();
		this.responseHandler = new TestMessageHandler();
		this.responseChannel.subscribe(this.responseHandler);
		this.eventPublisher = new TestEventPublisher();
		startActiveMqBroker();
		createAndStartRelay();
	}
