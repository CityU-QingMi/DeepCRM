	@Test
	public void optionalHeaderArgumentResolutionWhenNotPresent() {
		Message<?> message = createMessage("/pre/optionalHeaders");
		this.messageHandler.registerHandler(this.testController);
		this.messageHandler.handleMessage(message);

		assertEquals("optionalHeaders", this.testController.method);
		assertNull(this.testController.arguments.get("foo1"));
		assertNull(this.testController.arguments.get("foo2"));
	}
