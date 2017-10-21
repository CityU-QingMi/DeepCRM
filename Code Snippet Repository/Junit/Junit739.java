	@Test
	void allDiscoveredTestsAreInvokedForNullArgument() throws Exception {
		ProviderParameters providerParameters = providerParametersMock(TestClass1.class, TestClass2.class);
		Launcher launcher = LauncherFactory.create();
		JUnitPlatformProvider provider = new JUnitPlatformProvider(providerParameters, launcher);

		TestPlanSummaryListener executionListener = new TestPlanSummaryListener();
		launcher.registerTestExecutionListeners(executionListener);

		provider.invoke(null);

		assertThat(executionListener.summaries).hasSize(2);
		TestClass1.verifyExecutionSummary(executionListener.summaries.get(0));
		TestClass2.verifyExecutionSummary(executionListener.summaries.get(1));
	}
