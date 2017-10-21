	@Before
	public void setup() {

		TestMessageChannelDestinationResolver resolver = new TestMessageChannelDestinationResolver();

		this.myChannel = new ExecutorSubscribableChannel();
		resolver.registerMessageChannel("myChannel", this.myChannel);

		this.template = new TestDestinationResolvingMessagingTemplate();
		this.template.setDestinationResolver(resolver);

		this.headers = Collections.<String, Object>singletonMap("key", "value");

		this.postProcessor = new TestMessagePostProcessor();
	}
