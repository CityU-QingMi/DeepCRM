	@Override
	public NaturalIdRegionAccessStrategy createNaturalIdRegionAccessStrategy(
			EhcacheNaturalIdRegion naturalIdRegion,
			AccessType accessType) {
		return new NonstopAwareNaturalIdRegionAccessStrategy(
				actualFactory.createNaturalIdRegionAccessStrategy(
						naturalIdRegion,
						accessType
				), HibernateNonstopCacheExceptionHandler.getInstance()
		);
	}
