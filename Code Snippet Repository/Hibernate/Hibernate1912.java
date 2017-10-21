	public String traceLoggableFormat() {
		final StringBuilder buffer = new StringBuilder()
				.append( "ResultSetMappingDefinition[\n" )
				.append( "    name=" ).append( name ).append( "\n" )
				.append( "    returns=[\n" );

		for ( NativeSQLQueryReturn rtn : queryReturns ) {
			rtn.traceLog(
					new NativeSQLQueryReturn.TraceLogger() {
						@Override
						public void writeLine(String traceLine) {
							buffer.append( "        " ).append( traceLine ).append( "\n" );
						}
					}
			);
		}

		buffer.append( "    ]\n" ).append( "]" );

		return buffer.toString();
	}
