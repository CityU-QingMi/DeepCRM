	@Test
	void writesFileForSkippedContainer(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("test", () -> fail("never called"));
		engine.getEngineDescriptor().markSkipped("should be skipped");

		executeTests(engine, tempDirectory);

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite name=\"dummy\" tests=\"1\" skipped=\"1\" failures=\"0\" errors=\"0\"",
				"<testcase name=\"test\"",
				"<skipped>",
				"parent was skipped: should be skipped",
				"</skipped>",
				"</testcase>",
				"</testsuite>");
		//@formatter:on
	}
