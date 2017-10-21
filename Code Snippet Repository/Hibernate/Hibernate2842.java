	private String buildStatement() {
		String columnNames = String.join(",", columns);

		String singleIdValuesParam = '(' + String.join( ",", Collections.nCopies( columns.length, "?")) + ')';
		String parameters = String.join(",", Collections.nCopies(ids.size(), singleIdValuesParam));

		return new StringBuilder()
				.append( "with " )
				.append( tableName )
				.append( " (" )
				.append( columnNames )
				.append( " ) as ( select " )
				.append( columnNames )
				.append( " from ( values  " )
				.append( parameters )
				.append( ") as HT " )
				.append( "(" )
				.append( columnNames )
				.append( ") ) " )
				.toString();
	}
