	@Override
	public void traceLog(final TraceLogger logger) {
		logger.writeLine( "Constructor[" );
		logger.writeLine( "    targetClass=" + targetClass + "," );
		logger.writeLine( "    columns=[" );

		TraceLogger nestedLogger = new TraceLogger() {
			@Override
			public void writeLine(String traceLine) {
				logger.writeLine( "    " + traceLine );
			}
		};

		for ( NativeSQLQueryScalarReturn columnReturn : columnReturns ) {
			columnReturn.traceLog( nestedLogger );
		}

		logger.writeLine( "    ]" );
		logger.writeLine( "]" );
	}
