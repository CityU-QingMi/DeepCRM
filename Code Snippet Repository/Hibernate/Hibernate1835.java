	@Override
	public String getQueryHintString(String sql, String hints) {
		final StringBuilder buffer = new StringBuilder(
				sql.length()
						+ hints.length() + 12
		);
		final int pos = sql.indexOf( ";" );
		if ( pos > -1 ) {
			buffer.append( sql.substring( 0, pos ) );
		}
		else {
			buffer.append( sql );
		}
		buffer.append( " OPTION (" ).append( hints ).append( ")" );
		if ( pos > -1 ) {
			buffer.append( ";" );
		}
		sql = buffer.toString();

		return sql;
	}
