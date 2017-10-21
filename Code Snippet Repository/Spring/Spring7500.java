	@Test
	public void dotPathSeparator() {
		DotPathSeparatorController controller = new DotPathSeparatorController();

		this.messageHandler.setPathMatcher(new AntPathMatcher("."));
		this.messageHandler.registerHandler(controller);
		this.messageHandler.setDestinationPrefixes(Arrays.asList("/app1", "/app2/"));

		Message<?> message = createMessage("/app1/pre.foo");
		this.messageHandler.registerHandler(this.testController);
		this.messageHandler.handleMessage(message);

		assertEquals("handleFoo", controller.method);

		message = createMessage("/app2/pre.foo");
		this.messageHandler.handleMessage(message);

		assertEquals("handleFoo", controller.method);
	}
