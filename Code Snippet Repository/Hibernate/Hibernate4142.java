	protected UniqueEntityLoader getAppropriateLoader(LockOptions lockOptions, SharedSessionContractImplementor session) {
		if ( queryLoader != null ) {
			// if the user specified a custom query loader we need to that
			// regardless of any other consideration
			return queryLoader;
		}
		else if ( isAffectedByEnabledFilters( session ) ) {
			// because filters affect the rows returned (because they add
			// restrictions) these need to be next in precedence
			return createEntityLoader( lockOptions, session.getLoadQueryInfluencers() );
		}
		else if ( session.getLoadQueryInfluencers().getInternalFetchProfile() != null && LockMode.UPGRADE.greaterThan(
				lockOptions.getLockMode()
		) ) {
			// Next, we consider whether an 'internal' fetch profile has been set.
			// This indicates a special fetch profile Hibernate needs applied
			// (for its merge loading process e.g.).
			return (UniqueEntityLoader) getLoaders().get( session.getLoadQueryInfluencers().getInternalFetchProfile() );
		}
		else if ( isAffectedByEnabledFetchProfiles( session ) ) {
			// If the session has associated influencers we need to adjust the
			// SQL query used for loading based on those influencers
			return createEntityLoader( lockOptions, session.getLoadQueryInfluencers() );
		}
		else if ( isAffectedByEntityGraph( session ) ) {
			return createEntityLoader( lockOptions, session.getLoadQueryInfluencers() );
		}
		else if ( lockOptions.getTimeOut() != LockOptions.WAIT_FOREVER ) {
			return createEntityLoader( lockOptions, session.getLoadQueryInfluencers() );
		}
		else {
			return (UniqueEntityLoader) getLoaders().get( lockOptions.getLockMode() );
		}
	}
