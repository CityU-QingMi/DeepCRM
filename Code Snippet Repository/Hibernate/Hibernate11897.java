	public String toQuotedString() {
		final StringBuilder stb = new StringBuilder();
		if ( params.isEmpty() ) {
			return "";
		}
		stb.append( '\'' );
		for ( Map.Entry<String, Object> kv : params.entrySet() ) {
			if ( kv.getValue() == null ) {
				continue;
			}
			stb.append( kv.getKey() ).append( "=" ).append( kv.getValue() ).append( " " );
		}
		stb.deleteCharAt( stb.length() - 1 );
		stb.append( '\'' );
		return stb.toString();
	}
