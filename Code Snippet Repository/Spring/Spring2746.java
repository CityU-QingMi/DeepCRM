	@Test
	public void testAspectsAndAdvisorNotAppliedToManySingletonsIsFastEnough() {
		Assume.group(TestGroup.PERFORMANCE);
		Assume.notLogging(factoryLog);
		GenericApplicationContext ac = new GenericApplicationContext();
		new XmlBeanDefinitionReader(ac).loadBeanDefinitions(new ClassPathResource(qName("aspectsPlusAdvisor.xml"),
				getClass()));
		for (int i = 0; i < 10000; i++) {
			ac.registerBeanDefinition("singleton" + i, new RootBeanDefinition(NestedTestBean.class));
		}
		StopWatch sw = new StopWatch();
		sw.start("Singleton Creation");
		ac.refresh();
		sw.stop();

		// What's a reasonable expectation for _any_ server or developer machine load?
		// 8 seconds?
		assertStopWatchTimeLimit(sw, 8000);
	}
