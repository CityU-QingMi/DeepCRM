	@Test
	public void handleMessageForDestinationWithDotSeparator() {
		AntPathMatcher pathMatcher = new AntPathMatcher();
		pathMatcher.setPathSeparator(".");
		this.resolver.setPathMatcher(pathMatcher);

		TestPrincipal user = new TestPrincipal("joe");
		String destination = "/user/joe/jms.queue.call";
		Message<?> message = createMessage(SimpMessageType.MESSAGE, user, "123", destination);
		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("jms.queue.call-user123", actual.getTargetDestinations().iterator().next());
		assertEquals("/user/jms.queue.call", actual.getSubscribeDestination());
	}
