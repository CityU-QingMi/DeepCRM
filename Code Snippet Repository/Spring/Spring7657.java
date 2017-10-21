	@Test
	public void handleSubscribeForDestinationWithoutLeadingSlash() {
		AntPathMatcher pathMatcher = new AntPathMatcher();
		pathMatcher.setPathSeparator(".");
		this.resolver.setPathMatcher(pathMatcher);

		TestPrincipal user = new TestPrincipal("joe");
		String destination = "/user/jms.queue.call";
		Message<?> message = createMessage(SimpMessageType.SUBSCRIBE, user, "123", destination);
		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("jms.queue.call-user123", actual.getTargetDestinations().iterator().next());
		assertEquals(destination, actual.getSubscribeDestination());
	}
