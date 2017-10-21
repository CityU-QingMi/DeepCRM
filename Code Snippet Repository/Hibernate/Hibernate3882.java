	void createPrimaryKey() {
		if ( !isOneToMany() ) {
			PrimaryKey pk = new PrimaryKey( getCollectionTable() );
			pk.addColumns( getIdentifier().getColumnIterator() );
			getCollectionTable().setPrimaryKey(pk);
		}
		else {
			// don't create a unique key, 'cos some
			// databases don't like a UK on nullable
			// columns
			//getCollectionTable().createUniqueKey( getIdentifier().getConstraintColumns() );
		}
		// create an index on the key columns??
	}
