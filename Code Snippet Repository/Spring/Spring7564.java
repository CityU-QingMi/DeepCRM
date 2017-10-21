	@Test
	public void test() {

		SubscribableChannel inChannel = new StubMessageChannel();
		MessageChannel outChannel = new StubMessageChannel();
		String[] prefixes = new String[] { "/foo", "/bar" };

		StompBrokerRelayRegistration registration = new StompBrokerRelayRegistration(inChannel, outChannel, prefixes);
		registration.setClientLogin("clientlogin");
		registration.setClientPasscode("clientpasscode");
		registration.setSystemLogin("syslogin");
		registration.setSystemPasscode("syspasscode");
		registration.setSystemHeartbeatReceiveInterval(123);
		registration.setSystemHeartbeatSendInterval(456);
		registration.setVirtualHost("example.org");

		StompBrokerRelayMessageHandler handler = registration.getMessageHandler(new StubMessageChannel());

		assertArrayEquals(prefixes, handler.getDestinationPrefixes().toArray(new String[2]));
		assertEquals("clientlogin", handler.getClientLogin());
		assertEquals("clientpasscode", handler.getClientPasscode());
		assertEquals("syslogin", handler.getSystemLogin());
		assertEquals("syspasscode", handler.getSystemPasscode());
		assertEquals(123, handler.getSystemHeartbeatReceiveInterval());
		assertEquals(456, handler.getSystemHeartbeatSendInterval());
		assertEquals("example.org", handler.getVirtualHost());
	}
