	@Test
	public void simpScope() {
		Map<String, Object> sessionAttributes = new ConcurrentHashMap<>();
		sessionAttributes.put("name", "value");

		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.create();
		headers.setSessionId("session1");
		headers.setSessionAttributes(sessionAttributes);
		headers.setDestination("/pre/scope");
		Message<?> message = MessageBuilder.withPayload(new byte[0]).setHeaders(headers).build();
		this.messageHandler.registerHandler(this.testController);
		this.messageHandler.handleMessage(message);

		assertEquals("scope", this.testController.method);
	}
