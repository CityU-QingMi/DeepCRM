	@Test
	public void handleMessageEncodedUserName() {
		String userName = "http://joe.openid.example.org/";

		TestSimpUser simpUser = new TestSimpUser(userName);
		simpUser.addSessions(new TestSimpSession("openid123"));
		when(this.registry.getUser(userName)).thenReturn(simpUser);

		String destination = "/user/" + StringUtils.replace(userName, "/", "%2F") + "/queue/foo";

		Message<?> message = createMessage(SimpMessageType.MESSAGE, new TestPrincipal("joe"), null, destination);
		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("/queue/foo-useropenid123", actual.getTargetDestinations().iterator().next());
	}
