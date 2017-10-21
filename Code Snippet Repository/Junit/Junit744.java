	private static ProviderParameters providerParametersMock(Class<?>... testClasses) {
		TestsToRun testsToRun = newTestsToRun(testClasses);

		ScanResult scanResult = mock(ScanResult.class);
		when(scanResult.applyFilter(any(), any())).thenReturn(testsToRun);

		RunOrderCalculator runOrderCalculator = mock(RunOrderCalculator.class);
		when(runOrderCalculator.orderTestClasses(any())).thenReturn(testsToRun);

		ReporterFactory reporterFactory = mock(ReporterFactory.class);
		RunListener runListener = mock(RunListener.class);
		when(reporterFactory.createReporter()).thenReturn(runListener);

		ProviderParameters providerParameters = mock(ProviderParameters.class);
		when(providerParameters.getScanResult()).thenReturn(scanResult);
		when(providerParameters.getRunOrderCalculator()).thenReturn(runOrderCalculator);
		when(providerParameters.getReporterFactory()).thenReturn(reporterFactory);

		return providerParameters;
	}
