	public static String buildSqlCreateIndexString(
			Dialect dialect,
			String name,
			String tableName,
			Iterator<Column> columns,
			java.util.Map<Column, String> columnOrderMap,
			boolean unique) {
		StringBuilder buf = new StringBuilder( "create" )
				.append( unique ? " unique" : "" )
				.append( " index " )
				.append( dialect.qualifyIndexName() ? name : StringHelper.unqualify( name ) )
				.append( " on " )
				.append( tableName )
				.append( " (" );
		while ( columns.hasNext() ) {
			Column column = columns.next();
			buf.append( column.getQuotedName( dialect ) );
			if ( columnOrderMap.containsKey( column ) ) {
				buf.append( " " ).append( columnOrderMap.get( column ) );
			}
			if ( columns.hasNext() ) {
				buf.append( ", " );
			}
		}
		buf.append( ")" );
		return buf.toString();
	}
