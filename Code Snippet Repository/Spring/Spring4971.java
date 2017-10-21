	@Test
	public void testSPR3173() throws Exception {
		Method bridgedMethod = UserDaoImpl.class.getDeclaredMethod("saveVararg", User.class, Object[].class);
		assertFalse(bridgedMethod.isBridge());

		Method bridgeMethod = UserDaoImpl.class.getDeclaredMethod("saveVararg", Object.class, Object[].class);
		assertTrue(bridgeMethod.isBridge());

		assertEquals(bridgedMethod, BridgeMethodResolver.findBridgedMethod(bridgeMethod));
	}
