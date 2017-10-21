	@Test
	public void testSimpleAdvice() {
		String expression = "execution(int org.springframework.tests.sample.beans.TestBean.getAge())";

		CallCountingInterceptor interceptor = new CallCountingInterceptor();

		TestBean testBean = getAdvisedProxy(expression, interceptor);

		assertEquals("Calls should be 0", 0, interceptor.getCount());

		testBean.getAge();

		assertEquals("Calls should be 1", 1, interceptor.getCount());

		testBean.setAge(90);

		assertEquals("Calls should still be 1", 1, interceptor.getCount());
	}
