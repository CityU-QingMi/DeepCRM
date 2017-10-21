	private void renderIdentifierSelect(QuerySelect sql) {
		int size = returnedTypes.size();

		for ( int k = 0; k < size; k++ ) {
			String name = (String) returnedTypes.get( k );
			String suffix = size == 1 ? "" : Integer.toString( k ) + '_';
			sql.addSelectFragmentString( persisters[k].identifierSelectFragment( name, suffix ) );
		}

	}
