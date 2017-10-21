	@Test
	void junitAndSpringMethodInjectionCombined(@Autowired Cat kittyCat, TestInfo testInfo, ApplicationContext context,
			TestReporter testReporter) {

		assertNotNull(testInfo, "TestInfo should have been injected by JUnit");
		assertNotNull(testReporter, "TestReporter should have been injected by JUnit");

		assertNotNull(context, "ApplicationContext should have been injected by Spring");
		assertNotNull(kittyCat, "Cat should have been @Autowired by Spring");
	}
