	@Test
	public void optionalHeaderArgumentResolutionWhenPresent() {
		Map<String, Object> headers = Collections.singletonMap("foo", "bar");
		Message<?> message = createMessage("/pre/optionalHeaders", headers);
		this.messageHandler.registerHandler(this.testController);
		this.messageHandler.handleMessage(message);

		assertEquals("optionalHeaders", this.testController.method);
		assertEquals("bar", this.testController.arguments.get("foo1"));
		assertEquals("bar", this.testController.arguments.get("foo2"));
	}
