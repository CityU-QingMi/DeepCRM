	static RunnerTestDescriptorAwareFilter adapter(Filter filter) {
		return new RunnerTestDescriptorAwareFilter() {
			@Override
			void initialize(RunnerTestDescriptor runnerTestDescriptor) {
				// do nothing
			}

			@Override
			public boolean shouldRun(Description description) {
				return filter.shouldRun(description);
			}

			@Override
			public String describe() {
				return filter.describe();
			}
		};
	}
