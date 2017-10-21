	@Test
	void writesReportEntry() throws Exception {
		EngineDescriptor engineDescriptor = new EngineDescriptor(UniqueId.forEngine("engine"), "Engine");
		TestDescriptorStub testDescriptor = new TestDescriptorStub(UniqueId.root("test", "successfulTest"),
			"successfulTest");

		engineDescriptor.addChild(testDescriptor);
		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));
		XmlReportData reportData = new XmlReportData(testPlan, Clock.systemDefaultZone());

		reportData.addReportEntry(TestIdentifier.from(testDescriptor), ReportEntry.from("myKey", "myValue"));
		reportData.markFinished(testPlan.getTestIdentifier("[test:successfulTest]"), successful());

		StringWriter out = new StringWriter();
		new XmlReportWriter(reportData).writeXmlReport(getOnlyElement(testPlan.getRoots()), out);

		String content = ensureValidAccordingToJenkinsSchema(out.toString());

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<system-out>",
				"Report Entry #1 (timestamp: ",
				"- myKey: myValue",
				"</system-out>");
		//@formatter:on
	}
