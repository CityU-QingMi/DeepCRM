	public static Caching createCaching(JaxbHbmCacheType cacheElement) {
		if ( cacheElement == null ) {
			// I'd really rather this be UNKNOWN, but the annotation version resolves this to TRUE/FALSE
			return new Caching( TruthValue.FALSE );
		}

		final boolean cacheLazyProps = cacheElement.getInclude() == null
				|| !"non-lazy".equals( cacheElement.getInclude().value() );

		return new Caching(
				cacheElement.getRegion(),
				cacheElement.getUsage(),
				cacheLazyProps,
				TruthValue.TRUE
		);
	}
