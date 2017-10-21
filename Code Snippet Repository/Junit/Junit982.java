	@Test
	void printsExceptionWhenReportsDirCannotBeCreated(@Root Path tempDirectory) throws Exception {
		Path reportsDir = tempDirectory.resolve("dummy.txt");
		Files.write(reportsDir, singleton("content"));

		StringWriter out = new StringWriter();
		XmlReportsWritingListener listener = new XmlReportsWritingListener(reportsDir, new PrintWriter(out));

		listener.testPlanExecutionStarted(TestPlan.from(emptySet()));

		assertThat(out.toString()).containsSequence("Could not create reports directory", "FileAlreadyExistsException",
			"at ");
	}
