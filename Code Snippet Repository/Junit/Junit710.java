	private Root discoverRoot(LauncherDiscoveryRequest discoveryRequest, String phase) {
		Root root = new Root();

		for (TestEngine testEngine : this.testEngines) {
			// @formatter:off
			boolean engineIsExcluded = discoveryRequest.getEngineFilters().stream()
					.map(engineFilter -> engineFilter.apply(testEngine))
					.anyMatch(FilterResult::excluded);
			// @formatter:on

			if (engineIsExcluded) {
				logger.debug(() -> String.format(
					"Test discovery for engine '%s' was skipped due to an EngineFilter in phase '%s'.",
					testEngine.getId(), phase));
				continue;
			}

			logger.debug(() -> String.format("Discovering tests during Launcher %s phase in engine '%s'.", phase,
				testEngine.getId()));

			Optional<TestDescriptor> engineRoot = discoverEngineRoot(testEngine, discoveryRequest);
			engineRoot.ifPresent(rootDescriptor -> root.add(testEngine, rootDescriptor));
		}
		root.applyPostDiscoveryFilters(discoveryRequest);
		root.prune();
		return root;
	}
