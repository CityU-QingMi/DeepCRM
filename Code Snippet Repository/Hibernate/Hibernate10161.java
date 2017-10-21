	public void build(StringBuilder sb, Map<String, Object> updateParamValues) {
		sb.append( "update " ).append( entityName ).append( " " ).append( alias );
		sb.append( " set " );
		int i = 1;
		for ( String property : updates.keySet() ) {
			final String paramName = generateParameterName();
			sb.append( alias ).append( "." ).append( property ).append( " = " ).append( ":" ).append( paramName );
			updateParamValues.put( paramName, updates.get( property ) );
			if ( i < updates.size() ) {
				sb.append( ", " );
			}
			++i;
		}
		if ( !rootParameters.isEmpty() ) {
			sb.append( " where " );
			rootParameters.build( sb, updateParamValues );
		}
	}
