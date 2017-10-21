	private void createCriteriaSQLAliasMap() {
		int i = 0;
		for ( final Criteria crit : criteriaInfoMap.keySet() ) {
			final CriteriaInfoProvider value = criteriaInfoMap.get( crit );
			String alias = crit.getAlias();
			if ( alias == null ) {
				// the entity name
				alias = value.getName();
			}
			criteriaSQLAliasMap.put( crit, StringHelper.generateAlias( alias, i++ ) );
		}

		criteriaSQLAliasMap.put( rootCriteria, rootSQLAlias );
	}
