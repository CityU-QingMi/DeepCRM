	@Test
	void writesFileForSingleErroneousTest(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("failingTest", () -> {
			throw new RuntimeException("error occurred");
		});

		executeTests(engine, tempDirectory);

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite name=\"dummy\" tests=\"1\" skipped=\"0\" failures=\"0\" errors=\"1\"",
				"<testcase name=\"failingTest\"",
				"<error message=\"error occurred\" type=\"java.lang.RuntimeException\">",
				"RuntimeException: error occurred",
				"\tat ",
				"</error>",
				"</testcase>",
				"</testsuite>")
			.doesNotContain("<skipped")
			.doesNotContain("<failure");
		//@formatter:on
	}
