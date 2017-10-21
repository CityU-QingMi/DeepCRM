	public void configure(Map configValues) {
		cacheTransactionManager = ConfigurationHelper.getBoolean(
				AvailableSettings.JTA_CACHE_TM,
				configValues,
				canCacheTransactionManagerByDefault()
		);
		cacheUserTransaction = ConfigurationHelper.getBoolean(
				AvailableSettings.JTA_CACHE_UT,
				configValues,
				canCacheUserTransactionByDefault()
		);
	}
