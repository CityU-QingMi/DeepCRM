	@Test
	public void testAfterReturningAdvisorIsInvoked() {
		class SummingAfterAdvice implements AfterReturningAdvice {
			public int sum;
			@Override
			public void afterReturning(@Nullable Object returnValue, Method m, Object[] args, @Nullable Object target) throws Throwable {
				sum += ((Integer) returnValue).intValue();
			}
		}
		SummingAfterAdvice aa = new SummingAfterAdvice();
		@SuppressWarnings("serial")
		Advisor matchesInt = new StaticMethodMatcherPointcutAdvisor(aa) {
			@Override
			public boolean matches(Method m, @Nullable Class<?> targetClass) {
				return m.getReturnType() == int.class;
			}
		};
		TestBean target = new TestBean();
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(new NopInterceptor());
		pf.addAdvisor(matchesInt);
		assertEquals("Advisor was added", matchesInt, pf.getAdvisors()[1]);
		ITestBean proxied = (ITestBean) createProxy(pf);
		assertEquals(0, aa.sum);
		int i1 = 12;
		int i2 = 13;

		// Won't be advised
		proxied.setAge(i1);
		assertEquals(i1, proxied.getAge());
		assertEquals(i1, aa.sum);
		proxied.setAge(i2);
		assertEquals(i2, proxied.getAge());
		assertEquals(i1 + i2, aa.sum);
		assertEquals(i2, proxied.getAge());
	}
