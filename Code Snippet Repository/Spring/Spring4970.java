	@Test
	public void testSPR3041() throws Exception {
		Method bridgedMethod = BusinessDao.class.getDeclaredMethod("save", Business.class);
		assertFalse(bridgedMethod.isBridge());

		Method bridgeMethod = BusinessDao.class.getDeclaredMethod("save", Object.class);
		assertTrue(bridgeMethod.isBridge());

		assertEquals(bridgedMethod, BridgeMethodResolver.findBridgedMethod(bridgeMethod));
	}
