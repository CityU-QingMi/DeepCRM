	@Test
	void writesSystemProperties(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("test", () -> {
		});

		executeTests(engine, tempDirectory);

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite",
				"<properties>",
				"<property name=\"file.separator\" value=\"" + File.separator + "\"/>",
				"<property name=\"path.separator\" value=\"" + File.pathSeparator + "\"/>",
				"</properties>",
				"<testcase",
				"</testsuite>");
		//@formatter:on
	}
