	public String determineDecimalRepresentation(String text, int type) {
		String literalValue = text;
		if ( type == NUM_FLOAT ) {
			if ( literalValue.endsWith( "f" ) || literalValue.endsWith( "F" ) ) {
				literalValue = literalValue.substring( 0, literalValue.length() - 1 );
			}
		}
		else if ( type == NUM_DOUBLE ) {
			if ( literalValue.endsWith( "d" ) || literalValue.endsWith( "D" ) ) {
				literalValue = literalValue.substring( 0, literalValue.length() - 1 );
			}
		}
		else if ( type == NUM_BIG_DECIMAL ) {
			if ( literalValue.endsWith( "bd" ) || literalValue.endsWith( "BD" ) ) {
				literalValue = literalValue.substring( 0, literalValue.length() - 2 );
			}
		}

		final BigDecimal number;
		try {
			number = new BigDecimal( literalValue );
		}
		catch (Throwable t) {
			throw new HibernateException( "Could not parse literal [" + text + "] as big-decimal", t );
		}

		return DECIMAL_LITERAL_FORMAT.getFormatter().format( number );
	}
