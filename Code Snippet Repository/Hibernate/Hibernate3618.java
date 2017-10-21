	private String resolveCollectionProperties(
			String aliasName,
			String propertyName) {

		Map fieldResults = context.getPropertyResultsMapByAlias( aliasName );
		SQLLoadableCollection collectionPersister = context.getCollectionPersisterByAlias( aliasName );
		String collectionSuffix = context.getCollectionSuffixByAlias( aliasName );

		if ( "*".equals( propertyName ) ) {
			if( !fieldResults.isEmpty() ) {
				throw new QueryException("Using return-propertys together with * syntax is not supported.");
			}
			
			String selectFragment = collectionPersister.selectFragment( aliasName, collectionSuffix );
			aliasesFound++;
			return selectFragment 
						+ ", " 
						+ resolveProperties( aliasName, propertyName );
		}
		else if ( "element.*".equals( propertyName ) ) {
			return resolveProperties( aliasName, "*" );
		}
		else {
			String[] columnAliases;

			// Let return-propertys override whatever the persister has for aliases.
			columnAliases = ( String[] ) fieldResults.get(propertyName);
			if ( columnAliases==null ) {
				columnAliases = collectionPersister.getCollectionPropertyColumnAliases( propertyName, collectionSuffix );
			}
			
			if ( columnAliases == null || columnAliases.length == 0 ) {
				throw new QueryException(
						"No column name found for property [" + propertyName + "] for alias [" + aliasName + "]",
						originalQueryString
				);
			}
			if ( columnAliases.length != 1 ) {
				// TODO: better error message since we actually support composites if names are explicitly listed.
				throw new QueryException(
						"SQL queries only support properties mapped to a single column - property [" +
						propertyName + "] is mapped to " + columnAliases.length + " columns.",
						originalQueryString
				);
			}
			aliasesFound++;
			return columnAliases[0];
		
		}
	}
