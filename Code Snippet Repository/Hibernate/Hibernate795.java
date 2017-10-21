	public Table createTable(Identifier logicalTableName, boolean isAbstract) {
		final Table existing = tables.get( logicalTableName );
		if ( existing != null ) {
			return existing;
		}

		final Identifier physicalTableName = database.getPhysicalNamingStrategy().toPhysicalTableName( logicalTableName, database.getJdbcEnvironment() );
		Table table = new Table( this, physicalTableName, isAbstract );
		tables.put( logicalTableName, table );
		return table;
	}
