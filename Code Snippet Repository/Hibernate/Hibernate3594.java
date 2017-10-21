	private void createCriteriaEntityNameMap() {
		// initialize the rootProvider first
		final CriteriaInfoProvider rootProvider = new EntityCriteriaInfoProvider(
				(Queryable) sessionFactory.getEntityPersister( rootEntityName )
		);
		criteriaInfoMap.put( rootCriteria, rootProvider );
		nameCriteriaInfoMap.put( rootProvider.getName(), rootProvider );

		for ( final String key : associationPathCriteriaMap.keySet() ) {
			final Criteria value = associationPathCriteriaMap.get( key );
			final CriteriaInfoProvider info = getPathInfo( key );
			criteriaInfoMap.put( value, info );
			nameCriteriaInfoMap.put( info.getName(), info );
		}
	}
