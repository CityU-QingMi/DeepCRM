	@Test
	public void testSPR3304() throws Exception {
		Method bridgedMethod = MegaMessageProducerImpl.class.getDeclaredMethod("receive", MegaMessageEvent.class);
		assertFalse(bridgedMethod.isBridge());

		Method bridgeMethod  = MegaMessageProducerImpl.class.getDeclaredMethod("receive", MegaEvent.class);
		assertTrue(bridgeMethod.isBridge());

		assertEquals(bridgedMethod, BridgeMethodResolver.findBridgedMethod(bridgeMethod));
	}
