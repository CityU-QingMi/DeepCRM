	@Test
	public void handleMessageFromOwnBroadcast() throws Exception {

		TestSimpUser simpUser = new TestSimpUser("joe");
		simpUser.addSessions(new TestSimpSession("123"));
		when(this.localRegistry.getUserCount()).thenReturn(1);
		when(this.localRegistry.getUsers()).thenReturn(Collections.singleton(simpUser));

		assertEquals(1, this.multiServerRegistry.getUserCount());

		Message<?> message = this.converter.toMessage(this.multiServerRegistry.getLocalRegistryDto(), null);
		this.multiServerRegistry.addRemoteRegistryDto(message, this.converter, 20000);
		assertEquals(1, this.multiServerRegistry.getUserCount());
	}
