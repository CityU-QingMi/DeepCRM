	private Optional<TestDescriptor> discoverEngineRoot(TestEngine testEngine,
			LauncherDiscoveryRequest discoveryRequest) {

		UniqueId uniqueEngineId = UniqueId.forEngine(testEngine.getId());
		try {
			TestDescriptor engineRoot = testEngine.discover(discoveryRequest, uniqueEngineId);
			Preconditions.notNull(engineRoot,
				() -> String.format(
					"The discover() method for TestEngine with ID '%s' must return a non-null root TestDescriptor.",
					testEngine.getId()));
			return Optional.of(engineRoot);
		}
		catch (Throwable throwable) {
			handleThrowable(testEngine, "discover", throwable);
			return Optional.empty();
		}
	}
