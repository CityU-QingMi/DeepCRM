	protected final Properties buildProperties() {
		Properties props = new Properties();
		setUnlessNull( props, Environment.DIALECT, dialect );
		setUnlessNull( props, Environment.DEFAULT_SCHEMA, defaultSchema );
		setUnlessNull( props, Environment.DEFAULT_CATALOG, defaultCatalog );
		setUnlessNull( props, Environment.MAX_FETCH_DEPTH, maximumFetchDepth );
		setUnlessNull( props, Environment.STATEMENT_FETCH_SIZE, jdbcFetchSize );
		setUnlessNull( props, Environment.STATEMENT_BATCH_SIZE, jdbcBatchSize );
		setUnlessNull( props, Environment.BATCH_VERSIONED_DATA, batchVersionedDataEnabled );
		setUnlessNull( props, Environment.USE_SCROLLABLE_RESULTSET, jdbcScrollableResultSetEnabled );
		setUnlessNull( props, Environment.USE_GET_GENERATED_KEYS, getGeneratedKeysEnabled );
		setUnlessNull( props, Environment.USE_STREAMS_FOR_BINARY, streamsForBinaryEnabled );
		setUnlessNull( props, Environment.USE_REFLECTION_OPTIMIZER, reflectionOptimizationEnabled );
		setUnlessNull( props, Environment.QUERY_SUBSTITUTIONS, querySubstitutions );
		setUnlessNull( props, Environment.SHOW_SQL, showSqlEnabled );
		setUnlessNull( props, Environment.USE_SQL_COMMENTS, commentsEnabled );
		setUnlessNull( props, Environment.CACHE_REGION_FACTORY, cacheRegionFactory );
		setUnlessNull( props, Environment.CACHE_PROVIDER_CONFIG, cacheProviderConfig );
		setUnlessNull( props, Environment.CACHE_REGION_PREFIX, cacheRegionPrefix );
		setUnlessNull( props, Environment.USE_MINIMAL_PUTS, minimalPutsEnabled );
		setUnlessNull( props, Environment.USE_SECOND_LEVEL_CACHE, secondLevelCacheEnabled );
		setUnlessNull( props, Environment.USE_QUERY_CACHE, queryCacheEnabled );

		Map extraProperties = getExtraProperties();
		if ( extraProperties != null ) {
			addAll( props, extraProperties );
		}

		if ( additionalProperties != null ) {
			addAll( props, additionalProperties );
		}

		return props;
	}
