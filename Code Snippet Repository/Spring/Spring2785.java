	@Test
	public void testMixinWithIntroductionInfo() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory();
		pc.addInterface(ITestBean.class);
		// We don't use an IntroductionAdvisor, we can just add an advice that implements IntroductionInfo
		pc.addAdvice(new LockMixin());
		pc.setTarget(tb);

		testTestBeanIntroduction(pc);
	}
