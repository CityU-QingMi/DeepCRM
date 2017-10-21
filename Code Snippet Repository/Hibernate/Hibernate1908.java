	protected String uniqueConstraintSql(UniqueKey uniqueKey) {
		final StringBuilder sb = new StringBuilder();
		sb.append( "unique (" );
		final Iterator<org.hibernate.mapping.Column> columnIterator = uniqueKey.columnIterator();
		while ( columnIterator.hasNext() ) {
			final org.hibernate.mapping.Column column = columnIterator.next();
			sb.append( column.getQuotedName( dialect ) );
			if ( uniqueKey.getColumnOrderMap().containsKey( column ) ) {
				sb.append( " " ).append( uniqueKey.getColumnOrderMap().get( column ) );
			}
			if ( columnIterator.hasNext() ) {
				sb.append( ", " );
			}
		}

		return sb.append( ')' ).toString();
	}
