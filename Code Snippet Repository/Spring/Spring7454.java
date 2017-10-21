	@Before
	public void setup() {

		List<String> destinationPrefixes = Arrays.asList("/test");

		this.messageHandler = new TestMethodMessageHandler();
		this.messageHandler.setApplicationContext(new StaticApplicationContext());
		this.messageHandler.setDestinationPrefixes(destinationPrefixes);
		this.messageHandler.afterPropertiesSet();

		this.testController = new TestController();
		this.messageHandler.registerHandler(this.testController);
	}
