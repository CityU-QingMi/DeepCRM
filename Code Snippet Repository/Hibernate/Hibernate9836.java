	private void generateEntityNamesTrackingTableMapping(
			Element classMapping,
			String propertyName,
			String joinTableSchema,
			String joinTableCatalog,
			String joinTableName,
			String joinTablePrimaryKeyColumnName,
			String joinTableValueColumnName,
			String joinTableValueColumnType) {
		final Element set = classMapping.addElement( "set" );
		set.addAttribute( "name", propertyName );
		set.addAttribute( "table", joinTableName );
		set.addAttribute( "schema", joinTableSchema );
		set.addAttribute( "catalog", joinTableCatalog );
		set.addAttribute( "cascade", "persist, delete" );
		set.addAttribute( "fetch", "join" );
		set.addAttribute( "lazy", "false" );
		final Element key = set.addElement( "key" );
		key.addAttribute( "column", joinTablePrimaryKeyColumnName );
		final Element element = set.addElement( "element" );
		element.addAttribute( "type", joinTableValueColumnType );
		final Element column = element.addElement( "column" );
		column.addAttribute( "name", joinTableValueColumnName );
	}
