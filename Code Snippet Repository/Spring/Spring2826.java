	@Test
	public void testOneAdvisedObjectCallsAnother() {
		int age1 = 33;
		int age2 = 37;

		TestBean target1 = new TestBean();
		ProxyFactory pf1 = new ProxyFactory(target1);
		// Permit proxy and invocation checkers to get context from AopContext
		pf1.setExposeProxy(true);
		NopInterceptor di1 = new NopInterceptor();
		pf1.addAdvice(0, di1);
		pf1.addAdvice(1, new ProxyMatcherInterceptor());
		pf1.addAdvice(2, new CheckMethodInvocationIsSameInAndOutInterceptor());
		pf1.addAdvice(1, new CheckMethodInvocationViaThreadLocalIsSameInAndOutInterceptor());
		// Must be first
		pf1.addAdvice(0, ExposeInvocationInterceptor.INSTANCE);
		ITestBean advised1 = (ITestBean) pf1.getProxy();
		advised1.setAge(age1); // = 1 invocation

		TestBean target2 = new TestBean();
		ProxyFactory pf2 = new ProxyFactory(target2);
		pf2.setExposeProxy(true);
		NopInterceptor di2 = new NopInterceptor();
		pf2.addAdvice(0, di2);
		pf2.addAdvice(1, new ProxyMatcherInterceptor());
		pf2.addAdvice(2, new CheckMethodInvocationIsSameInAndOutInterceptor());
		pf2.addAdvice(1, new CheckMethodInvocationViaThreadLocalIsSameInAndOutInterceptor());
		pf2.addAdvice(0, ExposeInvocationInterceptor.INSTANCE);
		ITestBean advised2 = (ITestBean) createProxy(pf2);
		advised2.setAge(age2);
		advised1.setSpouse(advised2); // = 2 invocations

		assertEquals("Advised one has correct age", age1, advised1.getAge()); // = 3 invocations
		assertEquals("Advised two has correct age", age2, advised2.getAge());
		// Means extra call on advised 2
		assertEquals("Advised one spouse has correct age", age2, advised1.getSpouse().getAge()); // = 4 invocations on 1 and another one on 2

		assertEquals("one was invoked correct number of times", 4, di1.getCount());
		// Got hit by call to advised1.getSpouse().getAge()
		assertEquals("one was invoked correct number of times", 3, di2.getCount());
	}
