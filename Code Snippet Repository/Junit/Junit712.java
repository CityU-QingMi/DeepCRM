	private void execute(Root root, ConfigurationParameters configurationParameters,
			TestExecutionListener... listeners) {

		TestExecutionListenerRegistry listenerRegistry = buildListenerRegistryForExecution(listeners);
		TestPlan testPlan = TestPlan.from(root.getEngineDescriptors());
		TestExecutionListener testExecutionListener = listenerRegistry.getCompositeTestExecutionListener();
		testExecutionListener.testPlanExecutionStarted(testPlan);
		ExecutionListenerAdapter engineExecutionListener = new ExecutionListenerAdapter(testPlan,
			testExecutionListener);
		for (TestEngine testEngine : root.getTestEngines()) {
			TestDescriptor testDescriptor = root.getTestDescriptorFor(testEngine);
			execute(testEngine, new ExecutionRequest(testDescriptor, engineExecutionListener, configurationParameters));
		}
		testExecutionListener.testPlanExecutionFinished(testPlan);
	}
