	@Test
	public void testFindBridgedMethod() throws Exception {
		Method unbridged = MyFoo.class.getDeclaredMethod("someMethod", String.class, Object.class);
		Method bridged = MyFoo.class.getDeclaredMethod("someMethod", Serializable.class, Object.class);
		assertFalse(unbridged.isBridge());
		assertTrue(bridged.isBridge());

		assertEquals("Unbridged method not returned directly", unbridged, BridgeMethodResolver.findBridgedMethod(unbridged));
		assertEquals("Incorrect bridged method returned", unbridged, BridgeMethodResolver.findBridgedMethod(bridged));
	}
