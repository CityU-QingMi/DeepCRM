	@Test
	void writesFileForSingleSucceedingTest(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("succeedingTest", "display<-->Name 😎", () -> {
		});

		executeTests(engine, tempDirectory);

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));
		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite name=\"dummy\" tests=\"1\" skipped=\"0\" failures=\"0\" errors=\"0\"",

					"<testcase name=\"display&lt;--&gt;Name 😎\" classname=\"dummy\"",
						"<system-out>",
							"unique-id: [engine:dummy]/[test:succeedingTest]",
							"display-name: display<-->Name 😎",
						"</system-out>",
					"</testcase>",

					"<system-out>",
						"unique-id: [engine:dummy]",
						"display-name: dummy",
					"</system-out>",
				"</testsuite>")
			.doesNotContain("<skipped")
			.doesNotContain("<failure")
			.doesNotContain("<error");
		//@formatter:on
	}
