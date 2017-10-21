	@Test
	public void testDynamicMethodPointcutThatAppliesStaticallyOnlyToSetters() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory();
		pc.addInterface(ITestBean.class);
		// Could apply dynamically to getAge/setAge but not to getName
		TestDynamicPointcutForSettersOnly dp = new TestDynamicPointcutForSettersOnly(new NopInterceptor(), "Age");
		pc.addAdvisor(dp);
		this.mockTargetSource.setTarget(tb);
		pc.setTargetSource(mockTargetSource);
		ITestBean it = (ITestBean) createProxy(pc);
		assertEquals(dp.count, 0);
		it.getAge();
		// Statically vetoed
		assertEquals(0, dp.count);
		it.setAge(11);
		assertEquals(it.getAge(), 11);
		assertEquals(dp.count, 1);
		// Applies statically but not dynamically
		it.setName("joe");
		assertEquals(dp.count, 1);
	}
