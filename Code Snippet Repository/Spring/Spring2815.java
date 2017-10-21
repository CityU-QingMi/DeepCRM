	@Test
	public void testMultiAdvice() throws Throwable {
		CountingMultiAdvice cca = new CountingMultiAdvice();
		@SuppressWarnings("serial")
		Advisor matchesNoArgs = new StaticMethodMatcherPointcutAdvisor(cca) {
			@Override
			public boolean matches(Method m, @Nullable Class<?> targetClass) {
				return m.getParameterCount() == 0 || "exceptional".equals(m.getName());
			}
		};
		TestBean target = new TestBean();
		target.setAge(80);
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(new NopInterceptor());
		pf.addAdvisor(matchesNoArgs);
		assertEquals("Advisor was added", matchesNoArgs, pf.getAdvisors()[1]);
		ITestBean proxied = (ITestBean) createProxy(pf);

		assertEquals(0, cca.getCalls());
		assertEquals(0, cca.getCalls("getAge"));
		assertEquals(target.getAge(), proxied.getAge());
		assertEquals(2, cca.getCalls());
		assertEquals(2, cca.getCalls("getAge"));
		assertEquals(0, cca.getCalls("setAge"));
		// Won't be advised
		proxied.setAge(26);
		assertEquals(2, cca.getCalls());
		assertEquals(26, proxied.getAge());
		assertEquals(4, cca.getCalls());
		try {
			proxied.exceptional(new SpecializedUncheckedException("foo", (SQLException)null));
			fail("Should have thrown CannotGetJdbcConnectionException");
		}
		catch (SpecializedUncheckedException ex) {
			// expected
		}
		assertEquals(6, cca.getCalls());
	}
