	private String getFromAndWhereFormula(
			String tableName,
			Iterator<Selectable> collectionTableColumns,
			Iterator<Selectable> referencedEntityColumns) {
		String alias = "$alias$";
		StringBuilder fromAndWhereSb = new StringBuilder( " from " )
				.append( tableName )
				//.append(" as ") //Oracle doesn't support it in subqueries
				.append( " " )
				.append( alias ).append( " where " );
		while ( collectionTableColumns.hasNext() ) {
			Column colColumn = (Column) collectionTableColumns.next();
			Column refColumn = (Column) referencedEntityColumns.next();
			fromAndWhereSb.append( alias )
					.append( '.' )
					.append( refColumn.getQuotedName() )
					.append( '=' )
					.append( colColumn.getQuotedName() )
					.append( " and " );
		}
		return fromAndWhereSb.substring( 0, fromAndWhereSb.length() - 5 );
	}
