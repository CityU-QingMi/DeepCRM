	private static void applySqlString(
			boolean quiet,
			String sqlString,
			Formatter formatter,
			ExecutionOptions options,
			GenerationTarget... targets) {
		if ( !StringHelper.isEmpty( sqlString ) ) {
			String sqlStringFormatted = formatter.format( sqlString );
			for ( GenerationTarget target : targets ) {
				try {
					target.accept( sqlStringFormatted );
				}
				catch (CommandAcceptanceException e) {
					if ( !quiet ) {
						options.getExceptionHandler().handleException( e );
					}
					// otherwise ignore the exception
				}
			}
		}
	}
