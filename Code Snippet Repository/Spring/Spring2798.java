	@Test
	public void testCannotRemoveAdvisorWhenFrozen() throws Throwable {
		TestBean target = new TestBean();
		target.setAge(21);
		ProxyFactory pc = new ProxyFactory(target);
		assertFalse(pc.isFrozen());
		pc.addAdvice(new NopInterceptor());
		ITestBean proxied = (ITestBean) createProxy(pc);
		pc.setFrozen(true);
		Advised advised = (Advised) proxied;

		assertTrue(pc.isFrozen());
		try {
			advised.removeAdvisor(0);
			fail("Shouldn't be able to remove Advisor when frozen");
		}
		catch (AopConfigException ex) {
			assertTrue(ex.getMessage().contains("frozen"));
		}
		// Didn't get removed
		assertEquals(1, advised.getAdvisors().length);
		pc.setFrozen(false);
		// Can now remove it
		advised.removeAdvisor(0);
		// Check it still works: proxy factory state shouldn't have been corrupted
		assertEquals(target.getAge(), proxied.getAge());
		assertEquals(0, advised.getAdvisors().length);
	}
