	private String resolveAdditionalJoinCondition(String rhsTableAlias, String withClause, Joinable joinable, AssociationType associationType) {
		// turns out that the call to AssociationType#getOnCondition in the initial code really just translates to
		// calls to the Joinable.filterFragment() method where the Joinable is either the entity or
		// collection persister
		final String filter = associationType!=null?
				associationType.getOnCondition( rhsTableAlias, factory, buildingParameters.getQueryInfluencers().getEnabledFilters() ):
				joinable.filterFragment(
					rhsTableAlias,
					buildingParameters.getQueryInfluencers().getEnabledFilters()
		);

		if ( StringHelper.isEmpty( withClause ) && StringHelper.isEmpty( filter ) ) {
			return "";
		}
		else if ( StringHelper.isNotEmpty( withClause ) && StringHelper.isNotEmpty( filter ) ) {
			return filter + " and " + withClause;
		}
		else {
			// only one is non-empty...
			return StringHelper.isNotEmpty( filter ) ? filter : withClause;
		}
	}
