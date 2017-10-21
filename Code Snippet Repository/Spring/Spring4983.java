	@Test
	public void testSPR2763() throws Exception {
		Method bridgedMethod = AbstractDao.class.getDeclaredMethod("save", Object.class);
		assertFalse(bridgedMethod.isBridge());

		Method bridgeMethod = UserDaoImpl.class.getDeclaredMethod("save", User.class);
		assertTrue(bridgeMethod.isBridge());

		assertEquals(bridgedMethod, BridgeMethodResolver.findBridgedMethod(bridgeMethod));
	}
