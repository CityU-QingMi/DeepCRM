	private LauncherDiscoveryRequest instantiateRunnerAndCaptureGeneratedRequest(Class<?> testClass)
			throws InitializationError {
		Launcher launcher = mock(Launcher.class);
		ArgumentCaptor<LauncherDiscoveryRequest> captor = ArgumentCaptor.forClass(LauncherDiscoveryRequest.class);
		when(launcher.discover(captor.capture())).thenReturn(TestPlan.from(emptySet()));

		new JUnitPlatform(testClass, launcher);

		return captor.getValue();
	}
