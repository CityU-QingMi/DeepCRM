	@Test
	void omitsMessageAttributeForFailedTestWithThrowableWithoutMessage() throws Exception {
		EngineDescriptor engineDescriptor = new EngineDescriptor(UniqueId.forEngine("engine"), "Engine");
		engineDescriptor.addChild(new TestDescriptorStub(UniqueId.root("test", "failedTest"), "failedTest"));

		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));
		XmlReportData reportData = new XmlReportData(testPlan, Clock.systemDefaultZone());
		reportData.markFinished(testPlan.getTestIdentifier("[test:failedTest]"), failed(new NullPointerException()));

		StringWriter out = new StringWriter();
		new XmlReportWriter(reportData).writeXmlReport(getOnlyElement(testPlan.getRoots()), out);

		String content = ensureValidAccordingToJenkinsSchema(out.toString());
		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testcase name=\"failedTest\"",
				"<error type=\"java.lang.NullPointerException\">",
				"</testcase>");
		//@formatter:on
	}
