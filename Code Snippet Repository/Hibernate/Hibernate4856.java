	private static void applySqlString(
			String sqlString,
			Formatter formatter,
			ExecutionOptions options,
			GenerationTarget... targets) {
		if ( StringHelper.isEmpty( sqlString ) ) {
			return;
		}

		try {
			String sqlStringFormatted = formatter.format( sqlString );
			for ( GenerationTarget target : targets ) {
				target.accept( sqlStringFormatted );
			}
		}
		catch (CommandAcceptanceException e) {
			options.getExceptionHandler().handleException( e );
		}
	}
