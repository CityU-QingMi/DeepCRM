	@Test
	void printsExceptionWhenReportCouldNotBeWritten(@Root Path tempDirectory) throws Exception {
		EngineDescriptor engineDescriptor = new EngineDescriptor(UniqueId.forEngine("engine"), "Engine");

		Path xmlFile = tempDirectory.resolve("TEST-engine.xml");
		Files.createDirectories(xmlFile);

		StringWriter out = new StringWriter();
		XmlReportsWritingListener listener = new XmlReportsWritingListener(tempDirectory, new PrintWriter(out));

		listener.testPlanExecutionStarted(TestPlan.from(singleton(engineDescriptor)));
		listener.executionFinished(TestIdentifier.from(engineDescriptor), successful());

		assertThat(out.toString()).containsSequence("Could not write XML report", "Exception", "at ");
	}
