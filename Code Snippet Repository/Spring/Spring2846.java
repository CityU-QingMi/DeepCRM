	@Test
	public void testMultipleProxiesForIntroductionAdvisor() {
		TestBean target1 = new TestBean();
		target1.setAge(20);
		TestBean target2 = new TestBean();
		target2.setAge(21);

		ITestBean proxy1 = getIntroductionAdvisorProxy(target1);
		ITestBean proxy2 = getIntroductionAdvisorProxy(target2);
		assertSame("Incorrect duplicate creation of proxy classes", proxy1.getClass(), proxy2.getClass());
	}
