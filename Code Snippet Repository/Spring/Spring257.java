	@Test
	public void testDynamicMatchingProxy() {
		String expression = "execution(void org.springframework.tests.sample.beans.TestBean.setSomeNumber(Number)) && args(Double)";

		CallCountingInterceptor interceptor = new CallCountingInterceptor();

		TestBean testBean = getAdvisedProxy(expression, interceptor);

		assertEquals("Calls should be 0", 0, interceptor.getCount());

		testBean.setSomeNumber(new Double(30));

		assertEquals("Calls should be 1", 1, interceptor.getCount());

		testBean.setSomeNumber(new Integer(90));

		assertEquals("Calls should be 1", 1, interceptor.getCount());
	}
