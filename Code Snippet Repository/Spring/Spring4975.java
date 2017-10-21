	@Test
	public void testSPR3485() throws Exception {
		Method bridgedMethod = DomainObject.class.getDeclaredMethod(
				"method2", ParameterType.class, byte[].class);
		assertFalse(bridgedMethod.isBridge());

		Method bridgeMethod = DomainObject.class.getDeclaredMethod(
				"method2", Serializable.class, Object.class);
		assertTrue(bridgeMethod.isBridge());

		assertEquals(bridgedMethod, BridgeMethodResolver.findBridgedMethod(bridgeMethod));
	}
