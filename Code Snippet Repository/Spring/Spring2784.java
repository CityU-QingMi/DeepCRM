	@Test
	public void testMixinWithIntroductionAdvisor() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory();
		pc.addInterface(ITestBean.class);
		pc.addAdvisor(new LockMixinAdvisor());
		pc.setTarget(tb);

		testTestBeanIntroduction(pc);
	}
