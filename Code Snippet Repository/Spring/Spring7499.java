	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		SimpMessagingTemplate brokerTemplate = new SimpMessagingTemplate(this.channel);
		brokerTemplate.setMessageConverter(this.converter);

		this.messageHandler = new TestSimpAnnotationMethodMessageHandler(brokerTemplate, this.channel, this.channel);
		this.messageHandler.setApplicationContext(new StaticApplicationContext());
		this.messageHandler.setValidator(new StringTestValidator(TEST_INVALID_VALUE));
		this.messageHandler.afterPropertiesSet();

		this.testController = new TestController();
	}
