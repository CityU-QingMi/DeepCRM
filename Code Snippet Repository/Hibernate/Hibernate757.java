	public void overlay(Caching overrides) {
		if ( overrides == null ) {
			return;
		}

		this.requested = overrides.requested;
		this.accessType = overrides.accessType;
		this.region = overrides.region;
		this.cacheLazyProperties = overrides.cacheLazyProperties;
	}
