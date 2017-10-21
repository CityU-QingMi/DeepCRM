	@Test
	void printsNoDetailsIfTheyAreHidden() throws Exception {
		options.setDetails(Details.NONE);

		dummyTestEngine.addTest("failingTest", FAILING_BLOCK);

		ConsoleTestExecutor task = new ConsoleTestExecutor(options, () -> createLauncher(dummyTestEngine));
		task.execute(new PrintWriter(stringWriter));

		assertThat(stringWriter.toString()).doesNotContain("Test execution started.");
	}
