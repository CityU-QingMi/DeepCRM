	private void createAssociationPathCriteriaMap() {
		final Iterator<CriteriaImpl.Subcriteria> iter = rootCriteria.iterateSubcriteria();
		while ( iter.hasNext() ) {
			CriteriaImpl.Subcriteria crit = iter.next();
			String wholeAssociationPath = getWholeAssociationPath( crit );
			Object old = associationPathCriteriaMap.put( wholeAssociationPath, crit );
			if ( old != null ) {
				throw new QueryException( "duplicate association path: " + wholeAssociationPath );
			}
			JoinType joinType = crit.getJoinType();
			old = associationPathJoinTypesMap.put( wholeAssociationPath, joinType );
			if ( old != null ) {
				// TODO : not so sure this is needed...
				throw new QueryException( "duplicate association path: " + wholeAssociationPath );
			}
			if ( crit.getWithClause() != null ) {
				this.withClauseMap.put( wholeAssociationPath, crit.getWithClause() );
			}
		}
	}
