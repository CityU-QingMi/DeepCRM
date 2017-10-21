	@Test
	public void testSPR3357() throws Exception {
		Method bridgedMethod = ExtendsAbstractImplementsInterface.class.getDeclaredMethod(
				"doSomething", DomainObjectExtendsSuper.class, Object.class);
		assertFalse(bridgedMethod.isBridge());

		Method bridgeMethod = ExtendsAbstractImplementsInterface.class.getDeclaredMethod(
				"doSomething", DomainObjectSuper.class, Object.class);
		assertTrue(bridgeMethod.isBridge());

		assertEquals(bridgedMethod, BridgeMethodResolver.findBridgedMethod(bridgeMethod));
	}
