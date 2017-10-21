	@Test
	void writesFileForSingleAbortedTest(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("abortedTest", () -> assumeFalse(true, "deliberately aborted"));

		executeTests(engine, tempDirectory);

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite name=\"dummy\" tests=\"1\" skipped=\"1\" failures=\"0\" errors=\"0\"",
				"<testcase name=\"abortedTest\"",
				"<skipped>",
				"TestAbortedException: ",
				"deliberately aborted",
				"at ",
				"</skipped>",
				"</testcase>",
				"</testsuite>")
			.doesNotContain("<failure")
			.doesNotContain("<error");
		//@formatter:on
	}
