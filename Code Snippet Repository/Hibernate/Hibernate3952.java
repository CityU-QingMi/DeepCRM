	public ForeignKey createForeignKey(
			String keyName,
			List keyColumns,
			String referencedEntityName,
			String keyDefinition,
			List referencedColumns) {
		final ForeignKeyKey key = new ForeignKeyKey( keyColumns, referencedEntityName, referencedColumns );

		ForeignKey fk = foreignKeys.get( key );
		if ( fk == null ) {
			fk = new ForeignKey();
			fk.setTable( this );
			fk.setReferencedEntityName( referencedEntityName );
			fk.setKeyDefinition(keyDefinition);
			fk.addColumns( keyColumns.iterator() );
			if ( referencedColumns != null ) {
				fk.addReferencedColumns( referencedColumns.iterator() );
			}

			// NOTE : if the name is null, we will generate an implicit name during second pass processing
			// after we know the referenced table name (which might not be resolved yet).
			fk.setName( keyName );

			foreignKeys.put( key, fk );
		}

		if ( keyName != null ) {
			fk.setName( keyName );
		}

		return fk;
	}
