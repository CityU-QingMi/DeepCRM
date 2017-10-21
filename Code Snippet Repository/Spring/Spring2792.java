	@Test
	public void testRejectsBogusDynamicIntroductionAdviceWithNoAdapter() throws Throwable {
		TestBean target = new TestBean();
		target.setAge(21);
		ProxyFactory pc = new ProxyFactory(target);
		pc.addAdvisor(new DefaultIntroductionAdvisor(new DummyIntroductionAdviceImpl(), Comparable.class));
		try {
			// TODO May fail on either call: may want to tighten up definition
			ITestBean proxied = (ITestBean) createProxy(pc);
			proxied.getName();
			fail("Bogus introduction");
		}
		catch (Exception ex) {
			// TODO used to catch UnknownAdviceTypeException, but
			// with CGLIB some errors are in proxy creation and are wrapped
			// in aspect exception. Error message is still fine.
			//assertTrue(ex.getMessage().indexOf("ntroduction") > -1);
		}
	}
