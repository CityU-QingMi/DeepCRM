	@Test
	public void testAspectsAndAdvisorAppliedToPrototypeIsFastEnough() {
		Assume.group(TestGroup.PERFORMANCE);
		Assume.notLogging(factoryLog);
		ClassPathXmlApplicationContext ac = newContext("aspectsPlusAdvisor.xml");
		StopWatch sw = new StopWatch();
		sw.start("Prototype Creation");
		for (int i = 0; i < 10000; i++) {
			ITestBean shouldBeWeaved = (ITestBean) ac.getBean("adrian2");
			if (i < 10) {
				doTestAspectsAndAdvisorAreApplied(ac, shouldBeWeaved);
			}
		}
		sw.stop();

		// What's a reasonable expectation for _any_ server or developer machine load?
		// 9 seconds?
		assertStopWatchTimeLimit(sw, 9000);
	}
