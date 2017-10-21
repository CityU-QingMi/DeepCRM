	protected StringBuilder whereString(String alias, String[] columnNames, int batchSize) {
		if ( columnNames.length == 1 ) {
			// if not a composite key, use "foo in (?, ?, ?)" for batching
			// if no batch, and not a composite key, use "foo = ?"
			InFragment in = new InFragment().setColumn( alias, columnNames[0] );
			for ( int i = 0; i < batchSize; i++ ) {
				in.addValue( "?" );
			}
			return new StringBuilder( in.toFragmentString() );
		}
		else {
			//a composite key
			ConditionFragment byId = new ConditionFragment()
					.setTableAlias( alias )
					.setCondition( columnNames, "?" );

			StringBuilder whereString = new StringBuilder();
			if ( batchSize == 1 ) {
				// if no batch, use "foo = ? and bar = ?"
				whereString.append( byId.toFragmentString() );
			}
			else {
				// if a composite key, use "( (foo = ? and bar = ?) or (foo = ? and bar = ?) )" for batching
				whereString.append( '(' ); //TODO: unnecessary for databases with ANSI-style joins
				DisjunctionFragment df = new DisjunctionFragment();
				for ( int i = 0; i < batchSize; i++ ) {
					df.addCondition( byId );
				}
				whereString.append( df.toFragmentString() );
				whereString.append( ')' ); //TODO: unnecessary for databases with ANSI-style joins
			}
			return whereString;
		}
	}
