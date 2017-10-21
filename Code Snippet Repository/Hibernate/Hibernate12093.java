	protected void doLog(final Level level, final String loggerClassName, final Object message, final Object[] parameters, final Throwable thrown) {
		final org.apache.log4j.Level translatedLevel = translate( level );
		if ( interceptEnabled.get() ) {
			intercept( level, parameters == null || parameters.length == 0 ? String.valueOf( message ) : MessageFormat.format( String.valueOf( message ), parameters ), thrown );
		}
		if ( !logger.isEnabledFor( translatedLevel ) ) {
			return;
		}
		try {
			logger.log(
					loggerClassName,
					translatedLevel,
					parameters == null || parameters.length == 0
							? String.valueOf( message )
							: MessageFormat.format(String.valueOf( message ), parameters ),
					thrown
			);
		}
		catch (Throwable ignored) {
		}
	}
