	private Map buildTrueFalseReplacementMapForDialect() {
		HashMap replacements = new HashMap();
		try {
			String dialectTrueRepresentation = getDialect().toBooleanValueString( true );
			// if this call succeeds, then the dialect is saying to represent true/false as int values...
			Integer.parseInt( dialectTrueRepresentation );
			replacements.put( "true", "1" );
			replacements.put( "false", "0" );
		}
		catch( NumberFormatException nfe ) {
			// the Integer#parseInt call failed...
		}
		return replacements;
	}
