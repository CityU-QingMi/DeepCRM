	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		when(this.brokerChannel.send(any())).thenReturn(true);
		this.converter = new MappingJackson2MessageConverter();

		SimpMessagingTemplate brokerTemplate = new SimpMessagingTemplate(this.brokerChannel);
		brokerTemplate.setMessageConverter(this.converter);

		this.localRegistry = mock(SimpUserRegistry.class);
		this.multiServerRegistry = new MultiServerUserRegistry(this.localRegistry);

		this.handler = new UserRegistryMessageHandler(this.multiServerRegistry, brokerTemplate,
				"/topic/simp-user-registry", this.taskScheduler);
	}
