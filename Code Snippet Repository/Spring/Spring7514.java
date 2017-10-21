	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);

		SimpMessagingTemplate messagingTemplate = new SimpMessagingTemplate(this.messageChannel);
		messagingTemplate.setMessageConverter(new StringMessageConverter());
		this.handler = new SubscriptionMethodReturnValueHandler(messagingTemplate);

		SimpMessagingTemplate jsonMessagingTemplate = new SimpMessagingTemplate(this.messageChannel);
		jsonMessagingTemplate.setMessageConverter(new MappingJackson2MessageConverter());
		this.jsonHandler = new SubscriptionMethodReturnValueHandler(jsonMessagingTemplate);

		Method method = this.getClass().getDeclaredMethod("getData");
		this.subscribeEventReturnType = new MethodParameter(method, -1);

		method = this.getClass().getDeclaredMethod("getDataAndSendTo");
		this.subscribeEventSendToReturnType = new MethodParameter(method, -1);

		method = this.getClass().getDeclaredMethod("handle");
		this.messageMappingReturnType = new MethodParameter(method, -1);

		method = this.getClass().getDeclaredMethod("getJsonView");
		this.subscribeEventJsonViewReturnType = new MethodParameter(method, -1);
	}
