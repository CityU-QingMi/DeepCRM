	private String render(FilterAliasGenerator aliasGenerator, int filterIndex) {
		Map<String, String> aliasTableMap = filterAliasTableMaps[filterIndex];
		String condition = filterConditions[filterIndex];
		if ( filterAutoAliasFlags[filterIndex] ) {
			return StringHelper.replace(
					condition,
					FilterImpl.MARKER,
					aliasGenerator.getAlias( aliasTableMap.get( null ) )
			);
		}
		else if ( isTableFromPersistentClass( aliasTableMap ) ) {
			return condition.replace( "{alias}", aliasGenerator.getAlias( aliasTableMap.get( null ) ) );
		}
		else {
			for ( Map.Entry<String, String> entry : aliasTableMap.entrySet() ) {
				condition = condition.replace(
						"{" + entry.getKey() + "}",
						aliasGenerator.getAlias( entry.getValue() )
				);
			}
			return condition;
		}
	}
