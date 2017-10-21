	@Test
	public void testFindBridgedMethodInHierarchy() throws Exception {
		Method bridgeMethod = DateAdder.class.getMethod("add", Object.class);
		assertTrue(bridgeMethod.isBridge());
		Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(bridgeMethod);
		assertFalse(bridgedMethod.isBridge());
		assertEquals("add", bridgedMethod.getName());
		assertEquals(1, bridgedMethod.getParameterCount());
		assertEquals(Date.class, bridgedMethod.getParameterTypes()[0]);
	}
