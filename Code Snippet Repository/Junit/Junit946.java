	@Test
	void printsDetailsIfTheyAreNotHidden() throws Exception {
		options.setDetails(Details.FLAT);

		dummyTestEngine.addTest("failingTest", FAILING_BLOCK);

		ConsoleTestExecutor task = new ConsoleTestExecutor(options, () -> createLauncher(dummyTestEngine));
		task.execute(new PrintWriter(stringWriter));

		assertThat(stringWriter.toString()).contains("Test execution started.");
	}
