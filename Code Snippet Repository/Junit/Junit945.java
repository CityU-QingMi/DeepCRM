	@Test
	void printsSummary() throws Exception {
		dummyTestEngine.addTest("succeedingTest", SUCCEEDING_TEST);
		dummyTestEngine.addTest("failingTest", FAILING_BLOCK);

		ConsoleTestExecutor task = new ConsoleTestExecutor(options, () -> createLauncher(dummyTestEngine));
		task.execute(new PrintWriter(stringWriter));

		assertThat(stringWriter.toString()).contains("Test run finished after", "2 tests found", "0 tests skipped",
			"2 tests started", "0 tests aborted", "1 tests successful", "1 tests failed");
	}
