	@Test
	void printsFailuresEvenIfDetailsAreHidden() throws Exception {
		options.setDetails(Details.NONE);

		dummyTestEngine.addTest("failingTest", FAILING_BLOCK);
		dummyTestEngine.addContainer("failingContainer", FAILING_BLOCK);

		ConsoleTestExecutor task = new ConsoleTestExecutor(options, () -> createLauncher(dummyTestEngine));
		task.execute(new PrintWriter(stringWriter));

		assertThat(stringWriter.toString()).contains("Failures (2)", "failingTest", "failingContainer");
	}
