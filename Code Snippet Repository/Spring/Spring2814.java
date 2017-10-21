	@Test
	public void testBeforeAdvisorIsInvoked() {
		CountingBeforeAdvice cba = new CountingBeforeAdvice();
		@SuppressWarnings("serial")
		Advisor matchesNoArgs = new StaticMethodMatcherPointcutAdvisor(cba) {
			@Override
			public boolean matches(Method m, @Nullable Class<?> targetClass) {
				return m.getParameterCount() == 0;
			}
		};
		TestBean target = new TestBean();
		target.setAge(80);
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(new NopInterceptor());
		pf.addAdvisor(matchesNoArgs);
		assertEquals("Advisor was added", matchesNoArgs, pf.getAdvisors()[1]);
		ITestBean proxied = (ITestBean) createProxy(pf);
		assertEquals(0, cba.getCalls());
		assertEquals(0, cba.getCalls("getAge"));
		assertEquals(target.getAge(), proxied.getAge());
		assertEquals(1, cba.getCalls());
		assertEquals(1, cba.getCalls("getAge"));
		assertEquals(0, cba.getCalls("setAge"));
		// Won't be advised
		proxied.setAge(26);
		assertEquals(1, cba.getCalls());
		assertEquals(26, proxied.getAge());
	}
