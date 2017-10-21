	public static String loggable(Set<ValidationMode> modes) {
		if ( modes == null || modes.isEmpty() ) {
			return "[<empty>]";
		}
		StringBuilder buffer = new StringBuilder( "[" );
		String sep = "";
		for ( ValidationMode mode : modes ) {
			buffer.append( sep ).append( mode.externalForm );
			sep = ", ";
		}
		return buffer.append( "]" ).toString();
	}
