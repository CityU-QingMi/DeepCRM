	private void invokeSingleClass(Class<?> testClass, RunListener runListener) {
		SimpleReportEntry classEntry = new SimpleReportEntry(getClass().getName(), testClass.getName());
		runListener.testSetStarting(classEntry);

		LauncherDiscoveryRequest discoveryRequest = request() //
				.selectors(selectClass(testClass)) //
				.filters(includeAndExcludeFilters) //
				.configurationParameters(configurationParameters) //
				.build();
		launcher.execute(discoveryRequest);

		runListener.testSetCompleted(classEntry);
	}
