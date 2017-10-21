	private String determineIntegerRepresentation(String text, int type) {
		try {
			if ( type == NUM_BIG_INTEGER ) {
				String literalValue = text;
				if ( literalValue.endsWith( "bi" ) || literalValue.endsWith( "BI" ) ) {
					literalValue = literalValue.substring( 0, literalValue.length() - 2 );
				}
				return new BigInteger( literalValue ).toString();
			}
			if ( type == NUM_INT ) {
				try {
					return Integer.valueOf( text ).toString();
				}
				catch (NumberFormatException e) {
					LOG.tracev(
							"Could not format incoming text [{0}] as a NUM_INT; assuming numeric overflow and attempting as NUM_LONG",
							text
					);
				}
			}
			String literalValue = text;
			if ( literalValue.endsWith( "l" ) || literalValue.endsWith( "L" ) ) {
				literalValue = literalValue.substring( 0, literalValue.length() - 1 );
			}
			return Long.valueOf( literalValue ).toString();
		}
		catch (Throwable t) {
			throw new HibernateException( "Could not parse literal [" + text + "] as integer", t );
		}
	}
