	@Test
	void writesReportEntriesToSystemOutElement(@Root Path tempDirectory, TestReporter testReporter) throws Exception {
		EngineDescriptor engineDescriptor = new EngineDescriptor(UniqueId.forEngine("engine"), "Engine");
		engineDescriptor.addChild(new TestDescriptorStub(UniqueId.root("child", "test"), "test"));
		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));

		StringWriter out = new StringWriter();
		XmlReportsWritingListener listener = new XmlReportsWritingListener(tempDirectory, new PrintWriter(out));

		listener.testPlanExecutionStarted(testPlan);
		TestIdentifier testIdentifier = testPlan.getTestIdentifier("[child:test]");
		listener.executionStarted(testIdentifier);
		listener.reportingEntryPublished(testIdentifier, ReportEntry.from("foo", "bar"));
		Map<String, String> map = new LinkedHashMap<>();
		map.put("bar", "baz");
		map.put("qux", "foo");
		listener.reportingEntryPublished(testIdentifier, ReportEntry.from(map));
		listener.executionFinished(testIdentifier, successful());
		listener.executionFinished(testPlan.getTestIdentifier("[engine:engine]"), successful());

		String content = readValidXmlFile(tempDirectory.resolve("TEST-engine.xml"));
		//testReporter.publishEntry("xml", content);

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite",
				"<testcase",
				"<system-out>",
				"Report Entry #1 (timestamp: " + Year.now(),
				"- foo: bar\n",
				"Report Entry #2 (timestamp: " + Year.now(),
				"- bar: baz\n",
				"- qux: foo\n",
				"</system-out>",
				"</testcase>",
				"</testsuite>");
		//@formatter:on
	}
