	public static void main(String... args) {
		PrintWriter writer = new PrintWriter(System.out, true);
		ApiReportGenerator reportGenerator = new ApiReportGenerator();

		// scan all types below "org.junit" package
		ApiReport apiReport = reportGenerator.generateReport("org.junit");

		// ApiReportWriter reportWriter = new MarkdownApiReportWriter(apiReport);
		ApiReportWriter reportWriter = new AsciidocApiReportWriter(apiReport);
		// ApiReportWriter reportWriter = new HtmlApiReportWriter(apiReport);

		// reportWriter.printReportHeader(writer);

		// Print report for all Usage enum constants
		// reportWriter.printDeclarationInfo(writer, EnumSet.allOf(Usage.class));

		// Print report only for Experimental Usage constant
		reportWriter.printDeclarationInfo(writer, EnumSet.of(Status.EXPERIMENTAL));
	}
