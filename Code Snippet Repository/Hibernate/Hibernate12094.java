	protected void doLogf(final Level level, final String loggerClassName, final String format, final Object[] parameters, final Throwable thrown) {
		final org.apache.log4j.Level translatedLevel = translate( level );
		if ( interceptEnabled.get() ) {
			intercept( level, parameters == null ? format : String.format( format, parameters ), thrown );
		}
		if ( !logger.isEnabledFor( translatedLevel ) ) {
			return;
		}
		try {
			logger.log(
					loggerClassName,
					translatedLevel,
					parameters == null ? format : String.format( format, parameters ),
					thrown
			);
		}
		catch (Throwable ignored) {
		}
	}
