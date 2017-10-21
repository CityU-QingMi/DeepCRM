	@Test
	public void testFindBridgedMethodFromParent() throws Exception {
		Method loadFromParentBridge = SettingsDaoImpl.class.getMethod("loadFromParent");
		assertTrue(loadFromParentBridge.isBridge());

		Method loadFromParent = AbstractDaoImpl.class.getMethod("loadFromParent");
		assertFalse(loadFromParent.isBridge());

		assertEquals(loadFromParent, BridgeMethodResolver.findBridgedMethod(loadFromParentBridge));
	}
