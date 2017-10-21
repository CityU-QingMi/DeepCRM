	public static Caching createNaturalIdCaching(JaxbHbmNaturalIdCacheType cacheElement) {
		if ( cacheElement == null ) {
			return new Caching( TruthValue.UNKNOWN );
		}

		return new Caching(
				StringHelper.nullIfEmpty( cacheElement.getRegion() ),
				null,
				false,
				TruthValue.TRUE
		);
	}
