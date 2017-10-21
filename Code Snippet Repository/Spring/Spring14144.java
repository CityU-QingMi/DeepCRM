	@Test
	public void globalException() throws Exception {
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.create();
		headers.setSessionId("session1");
		headers.setSessionAttributes(new ConcurrentHashMap<>());
		headers.setDestination("/exception");
		Message<?> message = MessageBuilder.withPayload(new byte[0]).setHeaders(headers).build();
		this.messageHandler.handleMessage(message);

		TestControllerAdvice controllerAdvice = this.applicationContext.getBean(TestControllerAdvice.class);
		assertTrue(controllerAdvice.isExceptionHandled());
	}
