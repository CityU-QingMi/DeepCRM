	private static void applySqlStrings(
			boolean quiet,
			Iterator<String> sqlStrings,
			Formatter formatter,
			ExecutionOptions options,
			GenerationTarget... targets) {
		if ( sqlStrings != null ) {
			while ( sqlStrings.hasNext() ) {
				final String sqlString = sqlStrings.next();
				applySqlString( quiet, sqlString, formatter, options, targets );
			}
		}
	}
