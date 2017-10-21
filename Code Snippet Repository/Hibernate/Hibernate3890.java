	void createPrimaryKey() {
		if ( !isOneToMany() ) {
			PrimaryKey pk = new PrimaryKey( getCollectionTable() );
			pk.addColumns( getKey().getColumnIterator() );
			
			// index should be last column listed
			boolean isFormula = false;
			Iterator iter = getIndex().getColumnIterator();
			while ( iter.hasNext() ) {
				if ( ( (Selectable) iter.next() ).isFormula() ) {
					isFormula=true;
				}
			}
			if (isFormula) {
				//if it is a formula index, use the element columns in the PK
				pk.addColumns( getElement().getColumnIterator() );
			}
			else {
				pk.addColumns( getIndex().getColumnIterator() ); 
			}
			getCollectionTable().setPrimaryKey(pk);
		}
		else {
			// don't create a unique key, 'cos some
			// databases don't like a UK on nullable
			// columns
/**/
/**/
/**/
/**/
		}
	}
