	@Test
	void resultOfTestIdentifierWithoutReportedEventsIsFailureOfAncestor() {
		EngineDescriptor engineDescriptor = new EngineDescriptor(UniqueId.forEngine("engine"), "Engine");
		engineDescriptor.addChild(new TestDescriptorStub(UniqueId.root("child", "test"), "test"));
		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));

		XmlReportData reportData = new XmlReportData(testPlan, Clock.systemDefaultZone());
		TestExecutionResult failureOfAncestor = failed(new RuntimeException("failed!"));
		reportData.markFinished(testPlan.getTestIdentifier("[engine:engine]"), failureOfAncestor);

		Optional<TestExecutionResult> result = reportData.getResult(testPlan.getTestIdentifier("[child:test]"));

		assertThat(result).contains(failureOfAncestor);
	}
