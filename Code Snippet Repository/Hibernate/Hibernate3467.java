	protected final boolean isJoinFetchEnabledByProfile(OuterJoinLoadable persister, PropertyPath path, int propertyNumber) {
		if ( !getLoadQueryInfluencers().hasEnabledFetchProfiles() ) {
			// perf optimization
			return false;
		}

		// ugh, this stuff has to be made easier...
		final String fullPath = path.getFullPath();
		String rootPropertyName = persister.getSubclassPropertyName( propertyNumber );
		int pos = fullPath.lastIndexOf( rootPropertyName );
		String relativePropertyPath = pos >= 0
				? fullPath.substring( pos )
				: rootPropertyName;
		String fetchRole = persister.getEntityName() + "." + relativePropertyPath;

		for ( String profileName : getLoadQueryInfluencers().getEnabledFetchProfileNames() ) {
			final FetchProfile profile = getFactory().getFetchProfile( profileName );
			final Fetch fetch = profile.getFetchByRole( fetchRole );
			if ( fetch != null && Fetch.Style.JOIN == fetch.getStyle() ) {
				return true;
			}
		}
		return false;
	}
