	@Test
	void usesClassNamesForXmlReport() throws TestSetFailedException, InvocationTargetException {
		String[] classNames = { "org.junit.platform.surefire.provider.JUnitPlatformProviderTests$Sub1Tests",
				"org.junit.platform.surefire.provider.JUnitPlatformProviderTests$Sub2Tests" };
		ProviderParameters providerParameters = providerParametersMock(Sub1Tests.class, Sub2Tests.class);

		JUnitPlatformProvider jUnitPlatformProvider = new JUnitPlatformProvider(providerParameters);
		TestsToRun testsToRun = newTestsToRun(Sub1Tests.class, Sub2Tests.class);

		jUnitPlatformProvider.invoke(testsToRun);
		RunListener reporter = providerParameters.getReporterFactory().createReporter();

		ArgumentCaptor<ReportEntry> reportEntryArgumentCaptor = ArgumentCaptor.forClass(ReportEntry.class);
		verify(reporter, times(2)).testSucceeded(reportEntryArgumentCaptor.capture());

		List<ReportEntry> allValues = reportEntryArgumentCaptor.getAllValues();
		assertThat(allValues).extracting(ReportEntry::getSourceName).containsExactly(classNames);
	}
