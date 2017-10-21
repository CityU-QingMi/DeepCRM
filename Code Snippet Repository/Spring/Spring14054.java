	@Test
	public void customChannels() {
		loadBeanDefinitions("websocket-config-broker-customchannels.xml");

		SimpAnnotationMethodMessageHandler annotationMethodMessageHandler =
				this.appContext.getBean(SimpAnnotationMethodMessageHandler.class);

		Validator validator = annotationMethodMessageHandler.getValidator();
		assertNotNull(validator);
		assertSame(this.appContext.getBean("myValidator"), validator);
		assertThat(validator, Matchers.instanceOf(TestValidator.class));

		List<Class<? extends MessageHandler>> subscriberTypes =
				Arrays.<Class<? extends MessageHandler>>asList(SimpAnnotationMethodMessageHandler.class,
						UserDestinationMessageHandler.class, SimpleBrokerMessageHandler.class);

		testChannel("clientInboundChannel", subscriberTypes, 3);
		testExecutor("clientInboundChannel", 100, 200, 600);

		subscriberTypes = Collections.singletonList(SubProtocolWebSocketHandler.class);

		testChannel("clientOutboundChannel", subscriberTypes, 3);
		testExecutor("clientOutboundChannel", 101, 201, 601);

		subscriberTypes = Arrays.<Class<? extends MessageHandler>>asList(SimpleBrokerMessageHandler.class,
				UserDestinationMessageHandler.class);

		testChannel("brokerChannel", subscriberTypes, 1);
		testExecutor("brokerChannel", 102, 202, 602);
	}
