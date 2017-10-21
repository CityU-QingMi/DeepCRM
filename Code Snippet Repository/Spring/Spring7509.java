	@Test
	public void messageMappingDestinationVariableResolution() {
		Message<?> message = createMessage("/pre/message/bar/value");
		this.messageHandler.registerHandler(this.testController);
		this.messageHandler.handleMessage(message);

		assertEquals("messageMappingDestinationVariable", this.testController.method);
		assertEquals("bar", this.testController.arguments.get("foo"));
		assertEquals("value", this.testController.arguments.get("name"));
	}
