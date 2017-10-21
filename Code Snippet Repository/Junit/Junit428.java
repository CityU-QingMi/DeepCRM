	@Test
	void childrenHaveBridgeMethods() throws Exception {
		assertFalse(ChildWithBridgeMethods.class.getMethod("anotherBeforeEach").isBridge());
		assertFalse(ChildWithBridgeMethods.class.getMethod("anotherAfterEach").isBridge());
		assertTrue(ChildWithBridgeMethods.class.getMethod("beforeEach").isBridge());
		assertTrue(ChildWithBridgeMethods.class.getMethod("afterEach").isBridge());

		assertTrue(ByteTestCase.class.getDeclaredMethod("test", Number.class).isBridge());
		assertFalse(ByteTestCase.class.getDeclaredMethod("test", Byte.class).isBridge());

		assertTrue(ShortTestCase.class.getDeclaredMethod("test", Number.class).isBridge());
		assertFalse(ShortTestCase.class.getDeclaredMethod("test", Short.class).isBridge());
	}
