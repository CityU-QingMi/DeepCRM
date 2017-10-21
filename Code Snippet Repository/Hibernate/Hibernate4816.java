	protected Map<Identifier, ForeignKeyInformation> foreignKeys() {
		if ( foreignKeys == null ) {
			final Map<Identifier, ForeignKeyInformation> fkMap = new HashMap<>();
			final Iterable<ForeignKeyInformation> fks = extractor.getForeignKeys( this );
			for ( ForeignKeyInformation fk : fks ) {
				fkMap.put( fk.getForeignKeyIdentifier(), fk );
			}
			this.foreignKeys = fkMap;
		}
		return foreignKeys;
	}
