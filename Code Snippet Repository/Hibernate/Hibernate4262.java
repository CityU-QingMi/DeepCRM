	public static void resolveResultSetMappings(ResultSetMappingResolutionContext context, String... resultSetMappingNames) {
		for ( String resultSetMappingName : resultSetMappingNames ) {
			log.tracef( "Starting attempt resolve named result-set-mapping : %s", resultSetMappingName );
			final ResultSetMappingDefinition mapping = context.findResultSetMapping( resultSetMappingName );
			if ( mapping == null ) {
				throw new UnknownSqlResultSetMappingException( "Unknown SqlResultSetMapping [" + resultSetMappingName + "]" );
			}

			log.tracef( "Found result-set-mapping : %s", mapping.traceLoggableFormat() );

			context.addQueryReturns( mapping.getQueryReturns() );

			final SQLQueryReturnProcessor processor =
					new SQLQueryReturnProcessor( mapping.getQueryReturns(), context.getSessionFactory() );
			final SQLQueryReturnProcessor.ResultAliasContext processResult = processor.process();
			context.addQuerySpaces( processResult.collectQuerySpaces() );
		}
	}
