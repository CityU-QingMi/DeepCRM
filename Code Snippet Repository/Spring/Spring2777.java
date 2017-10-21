	@Test
	public void testValuesStick() {
		int age1 = 33;
		int age2 = 37;
		String name = "tony";

		TestBean target1 = new TestBean();
		target1.setAge(age1);
		ProxyFactory pf1 = new ProxyFactory(target1);
		pf1.addAdvisor(new DefaultPointcutAdvisor(new NopInterceptor()));
		pf1.addAdvisor(new DefaultPointcutAdvisor(new TimestampIntroductionInterceptor()));
		ITestBean tb = (ITestBean) pf1.getProxy();

		assertEquals(age1, tb.getAge());
		tb.setAge(age2);
		assertEquals(age2, tb.getAge());
		assertNull(tb.getName());
		tb.setName(name);
		assertEquals(name, tb.getName());
	}
