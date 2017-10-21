	@Test
	public void exceptionWithHandlerMethodArg() {
		Message<?> message = createMessage("/pre/illegalState");
		this.messageHandler.registerHandler(this.testController);
		this.messageHandler.handleMessage(message);

		assertEquals("handleExceptionWithHandlerMethodArg", this.testController.method);
		HandlerMethod handlerMethod = (HandlerMethod) this.testController.arguments.get("handlerMethod");
		assertNotNull(handlerMethod);
		assertEquals("illegalState", handlerMethod.getMethod().getName());
	}
