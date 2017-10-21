	@Override
	public Table addDenormalizedTable(
			String schemaName,
			String catalogName,
			String name,
			boolean isAbstract,
			String subselectFragment,
			Table includedTable) throws DuplicateMappingException {
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
			return new DenormalizedTable( namespace, logicalName, subselectFragment, isAbstract, includedTable );
		}
		else {
			Table table = namespace.locateTable( logicalName );
			if ( table != null ) {
				throw new DuplicateMappingException( DuplicateMappingException.Type.TABLE, logicalName.toString() );
			}
			else {
				table = namespace.createDenormalizedTable( logicalName, isAbstract, includedTable );
			}
			return table;
		}
	}
