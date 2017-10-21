	public DenormalizedTable createDenormalizedTable(Identifier logicalTableName, boolean isAbstract, Table includedTable) {
		final Table existing = tables.get( logicalTableName );
		if ( existing != null ) {
			// for now assume it is
			return (DenormalizedTable) existing;
		}

		final Identifier physicalTableName = database.getPhysicalNamingStrategy().toPhysicalTableName( logicalTableName, database.getJdbcEnvironment() );
		DenormalizedTable table = new DenormalizedTable( this, physicalTableName, isAbstract, includedTable );
		tables.put( logicalTableName, table );
		return table;
	}
