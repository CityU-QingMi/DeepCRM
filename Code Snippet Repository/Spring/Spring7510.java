	@Test
	public void subscribeEventDestinationVariableResolution() {
		Message<?> message = createMessage("/pre/sub/bar/value");
		this.messageHandler.registerHandler(this.testController);
		this.messageHandler.handleMessage(message);

		assertEquals("subscribeEventDestinationVariable", this.testController.method);
		assertEquals("bar", this.testController.arguments.get("foo"));
		assertEquals("value", this.testController.arguments.get("name"));
	}
