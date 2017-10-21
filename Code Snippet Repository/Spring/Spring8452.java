	@Test
	@Ignore("")
	public void runTestAndAssertCounters() throws Exception {
		TrackingTestNGTestListener listener = new TrackingTestNGTestListener();
		TestNG testNG = new TestNG();
		testNG.addListener((ITestNGListener) listener);
		testNG.setTestClasses(new Class<?>[] {this.clazz});
		testNG.setVerbose(0);
		testNG.run();

		String name = this.clazz.getSimpleName();

		assertEquals("tests started for [" + name + "] ==> ", this.expectedTestStartCount, listener.testStartCount);
		assertEquals("successful tests for [" + name + "] ==> ", this.expectedTestSuccessCount, listener.testSuccessCount);
		assertEquals("failed tests for [" + name + "] ==> ", this.expectedFailureCount, listener.testFailureCount);
		assertEquals("failed configurations for [" + name + "] ==> ",
				this.expectedFailedConfigurationsCount, listener.failedConfigurationsCount);
	}
