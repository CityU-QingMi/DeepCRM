	@Test
	void singleTestClassIsInvoked() throws Exception {
		Launcher launcher = LauncherFactory.create();
		JUnitPlatformProvider provider = new JUnitPlatformProvider(providerParametersMock(), launcher);

		TestPlanSummaryListener executionListener = new TestPlanSummaryListener();
		launcher.registerTestExecutionListeners(executionListener);

		provider.invoke(TestClass1.class);

		assertThat(executionListener.summaries).hasSize(1);
		TestClass1.verifyExecutionSummary(executionListener.summaries.get(0));
	}
