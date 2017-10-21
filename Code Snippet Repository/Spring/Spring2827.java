	@Test
	public void testReentrance() {
		int age1 = 33;

		TestBean target1 = new TestBean();
		ProxyFactory pf1 = new ProxyFactory(target1);
		NopInterceptor di1 = new NopInterceptor();
		pf1.addAdvice(0, di1);
		ITestBean advised1 = (ITestBean) createProxy(pf1);
		advised1.setAge(age1); // = 1 invocation
		advised1.setSpouse(advised1); // = 2 invocations

		assertEquals("one was invoked correct number of times", 2, di1.getCount());

		assertEquals("Advised one has correct age", age1, advised1.getAge()); // = 3 invocations
		assertEquals("one was invoked correct number of times", 3, di1.getCount());

		// = 5 invocations, as reentrant call to spouse is advised also
		assertEquals("Advised spouse has correct age", age1, advised1.getSpouse().getAge());

		assertEquals("one was invoked correct number of times", 5, di1.getCount());
	}
