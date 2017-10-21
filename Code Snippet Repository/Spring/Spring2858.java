	@Test
	public void testGlobalsCanAddAspectInterfaces() {
		AddedGlobalInterface agi = (AddedGlobalInterface) factory.getBean("autoInvoker");
		assertTrue(agi.globalsAdded() == -1);

		ProxyFactoryBean pfb = (ProxyFactoryBean) factory.getBean("&validGlobals");
		// Trigger lazy initialization.
		pfb.getObject();
		// 2 globals + 2 explicit
		assertEquals("Have 2 globals and 2 explicit advisors", 3, pfb.getAdvisors().length);

		ApplicationListener<?> l = (ApplicationListener<?>) factory.getBean("validGlobals");
		agi = (AddedGlobalInterface) l;
		assertTrue(agi.globalsAdded() == -1);

		try {
			agi = (AddedGlobalInterface) factory.getBean("test1");
			fail("Aspect interface should't be implemeneted without globals");
		}
		catch (ClassCastException ex) {
		}
	}
