	@Test
	public void testIsMBean() {
		// Correctly returns true for a class
		assertTrue(JmxUtils.isMBean(JmxClass.class));

		// Correctly returns false since JmxUtils won't navigate to the extended interface
		assertFalse(JmxUtils.isMBean(SpecializedJmxInterface.class));

		// Incorrectly returns true since it doesn't detect that this is an interface
		assertFalse(JmxUtils.isMBean(JmxInterface.class));
	}
