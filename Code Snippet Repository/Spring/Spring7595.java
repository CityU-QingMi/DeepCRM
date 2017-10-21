	@Before
	public void setUp() throws Exception {

		logger.debug("Setting up before '" + this.testName.getMethodName() + "'");

		int port = SocketUtils.findAvailableTcpPort(61613);

		this.activeMQBroker = new BrokerService();
		this.activeMQBroker.addConnector("stomp://127.0.0.1:" + port);
		this.activeMQBroker.setStartAsync(false);
		this.activeMQBroker.setPersistent(false);
		this.activeMQBroker.setUseJmx(false);
		this.activeMQBroker.getSystemUsage().getMemoryUsage().setLimit(1024 * 1024 * 5);
		this.activeMQBroker.getSystemUsage().getTempUsage().setLimit(1024 * 1024 * 5);
		this.activeMQBroker.start();

		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.afterPropertiesSet();

		this.client = new ReactorNettyTcpStompClient("127.0.0.1", port);
		this.client.setMessageConverter(new StringMessageConverter());
		this.client.setTaskScheduler(taskScheduler);
	}
