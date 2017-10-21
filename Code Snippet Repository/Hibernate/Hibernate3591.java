	private void createAliasCriteriaMap() {
		aliasCriteriaMap.put( rootCriteria.getAlias(), rootCriteria );
		Iterator<CriteriaImpl.Subcriteria> iter = rootCriteria.iterateSubcriteria();
		while ( iter.hasNext() ) {
			Criteria subcriteria = iter.next();
			if ( subcriteria.getAlias() != null ) {
				Object old = aliasCriteriaMap.put( subcriteria.getAlias(), subcriteria );
				if ( old != null ) {
					throw new QueryException( "duplicate alias: " + subcriteria.getAlias() );
				}
			}
		}
	}
