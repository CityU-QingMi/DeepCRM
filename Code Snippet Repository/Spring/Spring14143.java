	@Before
	public void setUp() throws Exception {
		this.applicationContext = new StaticApplicationContext();
		this.applicationContext.registerSingleton("controller", TestController.class);
		this.applicationContext.registerSingleton("controllerAdvice", TestControllerAdvice.class);
		this.applicationContext.refresh();

		SubscribableChannel channel = Mockito.mock(SubscribableChannel.class);
		SimpMessageSendingOperations brokerTemplate = new SimpMessagingTemplate(channel);

		this.messageHandler = new TestWebSocketAnnotationMethodMessageHandler(brokerTemplate, channel, channel);
		this.messageHandler.setApplicationContext(this.applicationContext);
		this.messageHandler.afterPropertiesSet();
	}
