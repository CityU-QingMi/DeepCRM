	@Test
	public void testSPR2583() throws Exception {
		Method bridgedMethod = MessageBroadcasterImpl.class.getMethod("receive", MessageEvent.class);
		assertFalse(bridgedMethod.isBridge());
		Method bridgeMethod = MessageBroadcasterImpl.class.getMethod("receive", Event.class);
		assertTrue(bridgeMethod.isBridge());

		Method otherMethod = MessageBroadcasterImpl.class.getMethod("receive", NewMessageEvent.class);
		assertFalse(otherMethod.isBridge());

		assertFalse("Match identified incorrectly", BridgeMethodResolver.isBridgeMethodFor(bridgeMethod, otherMethod, MessageBroadcasterImpl.class));
		assertTrue("Match not found correctly", BridgeMethodResolver.isBridgeMethodFor(bridgeMethod, bridgedMethod, MessageBroadcasterImpl.class));

		assertEquals(bridgedMethod, BridgeMethodResolver.findBridgedMethod(bridgeMethod));
	}
