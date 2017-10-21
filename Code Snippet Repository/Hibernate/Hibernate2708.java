	public Object getLiteralValue() {
		String text = getText();
		if ( getType() == QUOTED_STRING ) {
			text = text.substring( 1, text.length() -1 );
		}

		final Type inherentType = getDataType();
		if ( inherentType == null ) {
			return text;
		}

		return ( (SingleColumnType) inherentType ).fromStringValue( text );
	}
