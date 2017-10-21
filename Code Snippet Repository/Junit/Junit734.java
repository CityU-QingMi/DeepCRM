	private SimpleReportEntry createReportEntry(TestIdentifier testIdentifier, Optional<Throwable> throwable) {
		Optional<String> className = getClassName(testIdentifier);

		Optional<StackTraceWriter> stackTraceWriter = throwable.flatMap(
			t -> className.map(name -> new PojoStackTraceWriter(name, getMethodName(testIdentifier).orElse(""), t)));

		String source = sourceLegacyReportingName(testIdentifier);
		return new SimpleReportEntry(source, testIdentifier.getLegacyReportingName(), stackTraceWriter.orElse(null),
			null);
	}
