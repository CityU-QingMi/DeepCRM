	private void parseProperty(int prefixLoc, String key, String value) {
		final ConfigurationBuilder builder;
		int suffixLoc;
		if ( (suffixLoc = key.indexOf( CONFIG_SUFFIX )) != -1 && !key.equals( INFINISPAN_CONFIG_RESOURCE_PROP )) {
			String regionName = key.substring( prefixLoc + PREFIX.length(), suffixLoc );
			baseConfigurations.put(regionName, value);
		}
		else if ( (suffixLoc = key.indexOf( STRATEGY_SUFFIX )) != -1 ) {
			builder = getOrCreateConfig( prefixLoc, key, suffixLoc );
			builder.eviction().strategy( EvictionStrategy.valueOf(value) );
		}
		else if ( (suffixLoc = key.indexOf( WAKE_UP_INTERVAL_SUFFIX )) != -1
				|| (suffixLoc = key.indexOf(DEPRECATED_WAKE_UP_INTERVAL_SUFFIX)) != -1 ) {
			builder = getOrCreateConfig( prefixLoc, key, suffixLoc );
			builder.expiration().wakeUpInterval( Long.parseLong(value) );
		}
		else if ( (suffixLoc = key.indexOf( MAX_ENTRIES_SUFFIX )) != -1 ) {
			builder = getOrCreateConfig( prefixLoc, key, suffixLoc );
			builder.eviction().size( Long.parseLong(value) );
		}
		else if ( (suffixLoc = key.indexOf( LIFESPAN_SUFFIX )) != -1 ) {
			builder = getOrCreateConfig( prefixLoc, key, suffixLoc );
			builder.expiration().lifespan( Long.parseLong(value) );
		}
		else if ( (suffixLoc = key.indexOf( MAX_IDLE_SUFFIX )) != -1 ) {
			builder = getOrCreateConfig( prefixLoc, key, suffixLoc );
			builder.expiration().maxIdle( Long.parseLong(value) );
		}
	}
