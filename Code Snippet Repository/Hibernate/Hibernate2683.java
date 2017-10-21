	String renderIdentifierSelect(int size, int k) {
		checkInitialized();
		// Render the identifier select fragment using the table alias.
		if ( fromElement.getFromClause().isSubQuery() ) {
			// TODO: Replace this with a more elegant solution.
			String[] idColumnNames = ( persister != null ) ?
					( (Queryable) persister ).getIdentifierColumnNames() : new String[0];
			StringBuilder buf = new StringBuilder();
			for ( int i = 0; i < idColumnNames.length; i++ ) {
				buf.append( fromElement.getTableAlias() ).append( '.' ).append( idColumnNames[i] );
				if ( i != idColumnNames.length - 1 ) {
					buf.append( ", " );
				}
			}
			return buf.toString();
		}
		else {
			if ( persister == null ) {
				throw new QueryException( "not an entity" );
			}
			String fragment = ( (Queryable) persister ).identifierSelectFragment(
					getTableAlias(), getSuffix(
					size,
					k
			)
			);
			return trimLeadingCommaAndSpaces( fragment );
		}
	}
