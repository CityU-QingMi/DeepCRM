	@Test
	public void testCanAddAndRemoveAdvicesOnSingleton() {
		ITestBean it = (ITestBean) factory.getBean("test1");
		Advised pc = (Advised) it;
		it.getAge();
		NopInterceptor di = new NopInterceptor();
		pc.addAdvice(0, di);
		assertEquals(0, di.getCount());
		it.setAge(25);
		assertEquals(25, it.getAge());
		assertEquals(2, di.getCount());
	}
