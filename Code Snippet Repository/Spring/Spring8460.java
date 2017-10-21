	@Test
	public void runAllTestsConcurrently() throws Exception {
		final int FAILED = 0;
		final int ABORTED = 0;
		final int IGNORED = countAnnotatedMethods(Ignore.class);
		final int TESTS = countAnnotatedMethods(Test.class) - IGNORED;

		runTestsAndAssertCounters(new ParallelComputer(true, true), TESTS, FAILED, TESTS, IGNORED, ABORTED,
			this.testClasses);
	}
