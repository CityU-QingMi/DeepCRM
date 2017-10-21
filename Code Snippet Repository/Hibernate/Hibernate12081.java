	private Ignore buildIgnore(List<RequiresDialect> requiresDialects) {
		String ignoreMessage = "";
		for ( RequiresDialect requiresDialect : requiresDialects ) {
			ignoreMessage += getIgnoreMessage(
					"@RequiresDialect non-match",
					requiresDialect.comment(),
					requiresDialect.jiraKey()
			);
			ignoreMessage += System.lineSeparator();
		}
		return new IgnoreImpl( ignoreMessage );
	}
