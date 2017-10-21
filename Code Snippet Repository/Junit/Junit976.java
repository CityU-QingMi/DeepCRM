	@Test
	void writesEmptySkippedElementForSkippedTestWithoutReason() throws Exception {
		EngineDescriptor engineDescriptor = new EngineDescriptor(UniqueId.forEngine("engine"), "Engine");
		engineDescriptor.addChild(new TestDescriptorStub(UniqueId.root("test", "skippedTest"), "skippedTest"));

		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));
		XmlReportData reportData = new XmlReportData(testPlan, Clock.systemDefaultZone());
		reportData.markSkipped(testPlan.getTestIdentifier("[test:skippedTest]"), null);

		StringWriter out = new StringWriter();
		new XmlReportWriter(reportData).writeXmlReport(getOnlyElement(testPlan.getRoots()), out);

		String content = ensureValidAccordingToJenkinsSchema(out.toString());
		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testcase name=\"skippedTest\"",
				"<skipped/>",
				"</testcase>");
		//@formatter:on
	}
