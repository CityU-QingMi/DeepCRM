	public static StandardServiceRegistryBuilder buildCustomQueryCacheStandardServiceRegistryBuilder(
			  String regionPrefix,
			  String queryCacheName,
			  Class<? extends JtaPlatform> jtaPlatform) {
		final StandardServiceRegistryBuilder ssrb = buildBaselineStandardServiceRegistryBuilder(
				  regionPrefix, InfinispanRegionFactory.class, true, true, jtaPlatform
		);
		ssrb.applySetting( InfinispanRegionFactory.QUERY_CACHE_RESOURCE_PROP, queryCacheName );
		return ssrb;
	}
