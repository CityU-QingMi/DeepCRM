	@Override
	public void testPlanExecutionStarted(TestPlan testPlan) {
		this.reportData = new XmlReportData(testPlan, clock);
		try {
			Files.createDirectories(this.reportsDir);
		}
		catch (IOException e) {
			printException("Could not create reports directory: " + this.reportsDir, e);
		}
	}
