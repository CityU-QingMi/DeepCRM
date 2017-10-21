	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder( "sql: " ).append( sqlQueryString );
		if ( positionalParameterValues != null ) {
			buffer.append( "; parameters: " );
			for ( Object positionalParameterValue : positionalParameterValues ) {
				buffer.append( positionalParameterValue ).append( ", " );
			}
		}
		if ( namedParameters != null ) {
			buffer.append( "; named parameters: " ).append( namedParameters );
		}
		if ( filterKeys != null ) {
			buffer.append( "; filterKeys: " ).append( filterKeys );
		}
		if ( firstRow != null ) {
			buffer.append( "; first row: " ).append( firstRow );
		}
		if ( maxRows != null ) {
			buffer.append( "; max rows: " ).append( maxRows );
		}
		if ( customTransformer != null ) {
			buffer.append( "; transformer: " ).append( customTransformer );
		}
		return buffer.toString();
	}
