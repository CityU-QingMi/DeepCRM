	@Test
	public void testSPR3324() throws Exception {
		Method bridgedMethod = BusinessDao.class.getDeclaredMethod("get", Long.class);
		assertFalse(bridgedMethod.isBridge());

		Method bridgeMethod = BusinessDao.class.getDeclaredMethod("get", Object.class);
		assertTrue(bridgeMethod.isBridge());

		assertEquals(bridgedMethod, BridgeMethodResolver.findBridgedMethod(bridgeMethod));
	}
