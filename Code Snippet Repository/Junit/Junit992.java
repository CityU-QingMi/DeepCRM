	@Test
	void writesFileForFailingContainer(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("test", () -> fail("never called"));
		engine.getEngineDescriptor().setBeforeAllBehavior(() -> fail("failure before all tests"));

		executeTests(engine, tempDirectory);

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite name=\"dummy\" tests=\"1\" skipped=\"0\" failures=\"1\" errors=\"0\"",
				"<testcase name=\"test\"",
				"<failure message=\"failure before all tests\" type=\"" + AssertionFailedError.class.getName() + "\">",
				"AssertionFailedError: failure before all tests",
				"\tat",
				"</failure>",
				"</testcase>",
				"</testsuite>");
		//@formatter:on
	}
