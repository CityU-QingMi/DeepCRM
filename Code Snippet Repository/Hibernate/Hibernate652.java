	@Override
	public Table addTable(
			String schemaName,
			String catalogName,
			String name,
			String subselectFragment,
			boolean isAbstract) {
		final Namespace namespace = getDatabase().locateNamespace(
				getDatabase().toIdentifier( catalogName ),
				getDatabase().toIdentifier( schemaName )
		);

		// annotation binding depends on the "table name" for @Subselect bindings
		// being set into the generated table (mainly to avoid later NPE), but for now we need to keep that :(
		final Identifier logicalName;
		if ( name != null ) {
			logicalName = getDatabase().toIdentifier( name );
		}
		else {
			logicalName = null;
		}

		if ( subselectFragment != null ) {
			return new Table( namespace, logicalName, subselectFragment, isAbstract );
		}
		else {
			Table table = namespace.locateTable( logicalName );
			if ( table != null ) {
				if ( !isAbstract ) {
					table.setAbstract( false );
				}
				return table;
			}
			return namespace.createTable( logicalName, isAbstract );
		}
	}
