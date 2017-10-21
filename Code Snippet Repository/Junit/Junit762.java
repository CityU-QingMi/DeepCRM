	private RunnerTestDescriptor determineRunnerTestDescriptor(Class<?> testClass, Runner runner,
			List<RunnerTestDescriptorAwareFilter> filters, UniqueId engineId) {
		RunnerTestDescriptor runnerTestDescriptor = createCompleteRunnerTestDescriptor(testClass, runner, engineId);
		if (!filters.isEmpty()) {
			if (runner instanceof Filterable) {
				Filter filter = createOrFilter(filters, runnerTestDescriptor);
				Runner filteredRunner = runnerTestDescriptor.toRequest().filterWith(filter).getRunner();
				runnerTestDescriptor = createCompleteRunnerTestDescriptor(testClass, filteredRunner, engineId);
			}
			else {
				logger.warn(() -> "Runner " + runner.getClass().getName() //
						+ " (used on " + testClass.getName() + ") does not support filtering" //
						+ " and will therefore be run completely.");
			}
		}
		return runnerTestDescriptor;
	}
