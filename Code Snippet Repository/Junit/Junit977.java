	@Test
	void writesEmptyErrorElementForFailedTestWithoutCause() throws Exception {
		UniqueId uniqueId = UniqueId.forEngine("myEngineId");
		EngineDescriptor engineDescriptor = new EngineDescriptor(uniqueId, "Fancy Engine") {
			@Override
			public String getLegacyReportingName() {
				return "myEngine";
			}
		};
		engineDescriptor.addChild(new TestDescriptorStub(uniqueId.append("test", "failedTest"), "some fancy name") {
			@Override
			public String getLegacyReportingName() {
				return "failedTest";
			}
		});

		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));
		XmlReportData reportData = new XmlReportData(testPlan, Clock.systemDefaultZone());
		reportData.markFinished(testPlan.getTestIdentifier("[engine:myEngineId]/[test:failedTest]"), failed(null));

		StringWriter out = new StringWriter();
		new XmlReportWriter(reportData).writeXmlReport(getOnlyElement(testPlan.getRoots()), out);

		String content = ensureValidAccordingToJenkinsSchema(out.toString());
		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testcase name=\"failedTest\" classname=\"myEngine\"",
				"<error/>",
				"</testcase>");
		//@formatter:on
	}
