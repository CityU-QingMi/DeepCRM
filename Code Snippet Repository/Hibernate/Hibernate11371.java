	public void applyTo(ConfigurationBuilder builder) {
		if ( overridden.contains( "evictionStrategy" ) ) {
			builder.eviction().strategy( evictionStrategy );
		}
		if ( overridden.contains( "evictionWakeUpInterval" ) ) {
			builder.expiration().wakeUpInterval( evictionWakeUpInterval );
		}
		if ( overridden.contains( "evictionMaxEntries" ) ) {
			builder.eviction().maxEntries( evictionMaxEntries );
		}
		if ( overridden.contains( "expirationLifespan" ) ) {
			builder.expiration().lifespan( expirationLifespan );
		}
		if ( overridden.contains( "expirationMaxIdle" ) ) {
			builder.expiration().maxIdle( expirationMaxIdle );
		}
		if ( overridden.contains( "isExposeStatistics" ) && isExposeStatistics ) {
			builder.jmxStatistics().enable();
		}
	}
