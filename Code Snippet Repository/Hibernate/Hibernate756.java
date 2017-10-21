	public void overlay(CacheRegionDefinition overrides) {
		if ( overrides == null ) {
			return;
		}

		requested = TruthValue.TRUE;
		accessType = AccessType.fromExternalName( overrides.getUsage() );
		if ( StringHelper.isEmpty( overrides.getRegion() ) ) {
			region = overrides.getRegion();
		}
		// ugh, primitive boolean
		cacheLazyProperties = overrides.isCacheLazy();
	}
