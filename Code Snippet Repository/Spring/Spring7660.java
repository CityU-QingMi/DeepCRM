	@Test
	public void handleMessage() {
		TestPrincipal user = new TestPrincipal("joe");
		String sourceDestination = "/user/joe/queue/foo";
		Message<?> message = createMessage(SimpMessageType.MESSAGE, user, "123", sourceDestination);
		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(sourceDestination, actual.getSourceDestination());
		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("/queue/foo-user123", actual.getTargetDestinations().iterator().next());
		assertEquals("/user/queue/foo", actual.getSubscribeDestination());
		assertEquals(user.getName(), actual.getUser());
	}
