	public static FetchStyle determineFetchStyleByProfile(
			LoadQueryInfluencers loadQueryInfluencers,
			EntityPersister persister,
			PropertyPath path,
			int propertyNumber) {
		if ( !loadQueryInfluencers.hasEnabledFetchProfiles() ) {
			// perf optimization
			return null;
		}

		// ugh, this stuff has to be made easier...
		final String fullPath = path.getFullPath();
		final String rootPropertyName = ( (OuterJoinLoadable) persister ).getSubclassPropertyName( propertyNumber );
		int pos = fullPath.lastIndexOf( rootPropertyName );
		final String relativePropertyPath = pos >= 0
				? fullPath.substring( pos )
				: rootPropertyName;
		final String fetchRole = persister.getEntityName() + "." + relativePropertyPath;

		for ( String profileName : loadQueryInfluencers.getEnabledFetchProfileNames() ) {
			final FetchProfile profile = loadQueryInfluencers.getSessionFactory().getFetchProfile( profileName );
			final Fetch fetch = profile.getFetchByRole( fetchRole );
			if ( fetch != null && Fetch.Style.JOIN == fetch.getStyle() ) {
				return FetchStyle.JOIN;
			}
		}
		return null;
	}
