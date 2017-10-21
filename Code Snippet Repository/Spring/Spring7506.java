	@Test
	@SuppressWarnings("")
	public void headerArgumentResolution() {
		Map<String, Object> headers = Collections.singletonMap("foo", "bar");
		Message<?> message = createMessage("/pre/headers", headers);
		this.messageHandler.registerHandler(this.testController);
		this.messageHandler.handleMessage(message);

		assertEquals("headers", this.testController.method);
		assertEquals("bar", this.testController.arguments.get("foo"));
		assertEquals("bar", ((Map<String, Object>) this.testController.arguments.get("headers")).get("foo"));
	}
