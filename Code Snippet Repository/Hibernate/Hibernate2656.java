	public String[] getIdentityColumns() {
		checkInitialized();
		final String table = getTableAlias();
		if ( table == null ) {
			throw new IllegalStateException( "No table alias for node " + this );
		}

		final String propertyName = getIdentifierPropertyName();

		if ( getWalker().getStatementType() == HqlSqlTokenTypes.SELECT ) {
			return getPropertyMapping( propertyName ).toColumns( table, propertyName );
		}
		else {
			return getPropertyMapping( propertyName ).toColumns( propertyName );
		}
	}
