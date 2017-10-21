	@Override
	@SuppressWarnings("")
	public boolean accept(Class testClass) {
		// @formatter:off
		LauncherDiscoveryRequest discoveryRequest = request()
				.selectors(selectClass(testClass))
				.filters(includeAndExcludeFilters)
				.build();
		// @formatter:on
		TestPlan testPlan = launcher.discover(discoveryRequest);
		return testPlan.containsTests();
	}
