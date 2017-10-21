	@Test
	void resultOfTestIdentifierWithoutAnyReportedEventsIsEmpty() {
		EngineDescriptor engineDescriptor = new EngineDescriptor(UniqueId.forEngine("engine"), "Engine");
		engineDescriptor.addChild(new TestDescriptorStub(UniqueId.root("child", "test"), "test"));
		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));

		XmlReportData reportData = new XmlReportData(testPlan, Clock.systemDefaultZone());
		Optional<TestExecutionResult> result = reportData.getResult(testPlan.getTestIdentifier("[child:test]"));

		assertThat(result).isEmpty();
	}
