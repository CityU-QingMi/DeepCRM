	private void addLeftOuterJoinCondition(String on) {
		StringBuilder buf = new StringBuilder( on );
		for ( int i = 0; i < buf.length(); i++ ) {
			char character = buf.charAt( i );
			final boolean isInsertPoint = OPERATORS.contains( Character.valueOf( character ) )
					|| ( character == ' ' && buf.length() > i + 3 && "is ".equals( buf.substring( i + 1, i + 4 ) ) );
			if ( isInsertPoint ) {
				buf.insert( i, "(+)" );
				i += 3;
			}
		}
		addCondition( buf.toString() );
	}
