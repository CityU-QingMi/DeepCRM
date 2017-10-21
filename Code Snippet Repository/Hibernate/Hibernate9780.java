	@Override
	public Map<String, Object> getRegionCacheAttributes(String regionName) {
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put( "Enabled", isRegionCacheEnabled( regionName ) );
		result.put( "LoggingEnabled", isRegionCacheLoggingEnabled( regionName ) );
		result.put( "MaxTTISeconds", getRegionCacheMaxTTISeconds( regionName ) );
		result.put( "MaxTTLSeconds", getRegionCacheMaxTTLSeconds( regionName ) );
		result.put( "TargetMaxInMemoryCount", getRegionCacheTargetMaxInMemoryCount( regionName ) );
		result.put( "TargetMaxTotalCount", getRegionCacheTargetMaxTotalCount( regionName ) );
		result.put( "OrphanEvictionEnabled", isRegionCacheOrphanEvictionEnabled( regionName ) );
		result.put( "OrphanEvictionPeriod", getRegionCacheOrphanEvictionPeriod( regionName ) );
		return result;
	}
