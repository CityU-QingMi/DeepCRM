	public String getDropTableString(String tableName) {
		final StringBuilder buf = new StringBuilder( "drop table " );
		if ( supportsIfExistsBeforeTableName() ) {
			buf.append( "if exists " );
		}
		buf.append( tableName ).append( getCascadeConstraintsString() );
		if ( supportsIfExistsAfterTableName() ) {
			buf.append( " if exists" );
		}
		return buf.toString();
	}
