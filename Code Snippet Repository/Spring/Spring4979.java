	@Test
	public void testFindBridgedMethodFromMultipleBridges() throws Exception {
		Method loadWithObjectReturn = findMethodWithReturnType("load", Object.class, SettingsDaoImpl.class);
		assertNotNull(loadWithObjectReturn);

		Method loadWithSettingsReturn = findMethodWithReturnType("load", Settings.class, SettingsDaoImpl.class);
		assertNotNull(loadWithSettingsReturn);
		assertNotSame(loadWithObjectReturn, loadWithSettingsReturn);

		Method method = SettingsDaoImpl.class.getMethod("load");
		assertEquals(method, BridgeMethodResolver.findBridgedMethod(loadWithObjectReturn));
		assertEquals(method, BridgeMethodResolver.findBridgedMethod(loadWithSettingsReturn));
	}
