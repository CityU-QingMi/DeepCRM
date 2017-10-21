	@Test
	public void testUseAsHashKey() {
		TestBean target1 = new TestBean();
		ProxyFactory pf1 = new ProxyFactory(target1);
		pf1.addAdvice(new NopInterceptor());
		ITestBean proxy1 = (ITestBean) createProxy(pf1);

		TestBean target2 = new TestBean();
		ProxyFactory pf2 = new ProxyFactory(target2);
		pf2.addAdvisor(new DefaultIntroductionAdvisor(new TimestampIntroductionInterceptor()));
		ITestBean proxy2 = (ITestBean) createProxy(pf2);

		HashMap<ITestBean, Object> h = new HashMap<>();
		Object value1 = "foo";
		Object value2 = "bar";
		assertNull(h.get(proxy1));
		h.put(proxy1, value1);
		h.put(proxy2, value2);
		assertEquals(h.get(proxy1), value1);
		assertEquals(h.get(proxy2), value2);
	}
