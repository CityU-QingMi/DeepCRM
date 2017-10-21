	@Test
	public void handleSubscribe() {
		TestPrincipal user = new TestPrincipal("joe");
		String sourceDestination = "/user/queue/foo";

		Message<?> message = createMessage(SimpMessageType.SUBSCRIBE, user, "123", sourceDestination);
		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(sourceDestination, actual.getSourceDestination());
		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("/queue/foo-user123", actual.getTargetDestinations().iterator().next());
		assertEquals(sourceDestination, actual.getSubscribeDestination());
		assertEquals(user.getName(), actual.getUser());
	}
