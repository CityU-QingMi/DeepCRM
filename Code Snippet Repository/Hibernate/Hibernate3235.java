	public static String quote(String name) {
		if ( isEmpty( name ) || isQuoted( name ) ) {
			return name;
		}
		// Convert the JPA2 specific quoting character (double quote) to Hibernate's (back tick)
		else if ( name.startsWith( "\"" ) && name.endsWith( "\"" ) ) {
			name = name.substring( 1, name.length() - 1 );
		}

		return "`" + name + '`';
	}
