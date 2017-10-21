	public String generateImports() {
		StringBuilder builder = new StringBuilder();

		for ( String next : imports ) {
			// don't add automatically "imported" stuff
			if ( !isAutoImported( next ) ) {
				if ( staticImports.contains( next ) ) {
					builder.append( "import static " ).append( next ).append( ";" ).append( System.lineSeparator() );
				}
				else {
					builder.append( "import " ).append( next ).append( ";" ).append( System.lineSeparator() );
				}
			}
		}

		if ( builder.indexOf( "$" ) >= 0 ) {
			return builder.toString();
		}
		return builder.toString();
	}
