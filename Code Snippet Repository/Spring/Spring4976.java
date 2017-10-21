	@Test
	public void testFindBridgedVarargMethod() throws Exception {
		Method unbridged = MyFoo.class.getDeclaredMethod("someVarargMethod", String.class, Object[].class);
		Method bridged = MyFoo.class.getDeclaredMethod("someVarargMethod", Serializable.class, Object[].class);
		assertFalse(unbridged.isBridge());
		assertTrue(bridged.isBridge());

		assertEquals("Unbridged method not returned directly", unbridged, BridgeMethodResolver.findBridgedMethod(unbridged));
		assertEquals("Incorrect bridged method returned", unbridged, BridgeMethodResolver.findBridgedMethod(bridged));
	}
