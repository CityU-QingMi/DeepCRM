	@Test
	public void testCannotAddInterceptorWhenFrozen() throws Throwable {
		TestBean target = new TestBean();
		target.setAge(21);
		ProxyFactory pc = new ProxyFactory(target);
		assertFalse(pc.isFrozen());
		pc.addAdvice(new NopInterceptor());
		ITestBean proxied = (ITestBean) createProxy(pc);
		pc.setFrozen(true);
		try {
			pc.addAdvice(0, new NopInterceptor());
			fail("Shouldn't be able to add interceptor when frozen");
		}
		catch (AopConfigException ex) {
			assertTrue(ex.getMessage().indexOf("frozen") > -1);
		}
		// Check it still works: proxy factory state shouldn't have been corrupted
		assertEquals(target.getAge(), proxied.getAge());
		assertEquals(1, ((Advised) proxied).getAdvisors().length);
	}
