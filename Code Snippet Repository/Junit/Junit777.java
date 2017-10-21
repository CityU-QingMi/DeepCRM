	private void executeTests(DiscoverySelector selector) {
		Launcher launcher = LauncherFactory.create();
		launcher.registerTestExecutionListeners(new StatusTrackingListener());

		// @formatter:off
		launcher.execute(
			request()
				.selectors(selector)
				.filters(includeEngines("junit-vintage"))
				.build()
		);
		// @formatter:on
	}
