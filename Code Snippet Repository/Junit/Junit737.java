	@Test
	void allGivenTestsToRunAreInvoked() throws Exception {
		Launcher launcher = LauncherFactory.create();
		JUnitPlatformProvider provider = new JUnitPlatformProvider(providerParametersMock(), launcher);

		TestPlanSummaryListener executionListener = new TestPlanSummaryListener();
		launcher.registerTestExecutionListeners(executionListener);

		TestsToRun testsToRun = newTestsToRun(TestClass1.class, TestClass2.class);
		provider.invoke(testsToRun);

		assertThat(executionListener.summaries).hasSize(2);
		TestClass1.verifyExecutionSummary(executionListener.summaries.get(0));
		TestClass2.verifyExecutionSummary(executionListener.summaries.get(1));
	}
