	@Test
	public void testDoubleParameterization() throws Exception {
		Method objectBridge = MyBoo.class.getDeclaredMethod("foo", Object.class);
		Method serializableBridge = MyBoo.class.getDeclaredMethod("foo", Serializable.class);

		Method stringFoo = MyBoo.class.getDeclaredMethod("foo", String.class);
		Method integerFoo = MyBoo.class.getDeclaredMethod("foo", Integer.class);

		assertEquals("foo(String) not resolved.", stringFoo, BridgeMethodResolver.findBridgedMethod(objectBridge));
		assertEquals("foo(Integer) not resolved.", integerFoo, BridgeMethodResolver.findBridgedMethod(serializableBridge));
	}
