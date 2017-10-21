	@Test
	public void testAspectsAndAdvisorNotAppliedToPrototypeIsFastEnough() {
		Assume.group(TestGroup.PERFORMANCE);
		Assume.notLogging(factoryLog);
		ClassPathXmlApplicationContext ac = newContext("aspectsPlusAdvisor.xml");
		StopWatch sw = new StopWatch();
		sw.start("Prototype Creation");
		for (int i = 0; i < 100000; i++) {
			INestedTestBean shouldNotBeWeaved = (INestedTestBean) ac.getBean("i21");
			if (i < 10) {
				assertFalse(AopUtils.isAopProxy(shouldNotBeWeaved));
			}
		}
		sw.stop();

		// What's a reasonable expectation for _any_ server or developer machine load?
		// 3 seconds?
		assertStopWatchTimeLimit(sw, 6000);
	}
