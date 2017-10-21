	private EntityLoader getAppropriateUniqueKeyLoader(String propertyName, SharedSessionContractImplementor session) {
		final boolean useStaticLoader = !session.getLoadQueryInfluencers().hasEnabledFilters()
				&& !session.getLoadQueryInfluencers().hasEnabledFetchProfiles()
				&& propertyName.indexOf( '.' ) < 0; //ugly little workaround for fact that createUniqueKeyLoaders() does not handle component properties

		if ( useStaticLoader ) {
			return (EntityLoader) uniqueKeyLoaders.get( propertyName );
		}
		else {
			return createUniqueKeyLoader(
					propertyMapping.toType( propertyName ),
					propertyMapping.toColumns( propertyName ),
					session.getLoadQueryInfluencers()
			);
		}
	}
