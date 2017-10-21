	public static void addToCache(AdvancedCache cache, PutFromLoadValidator validator) {
		List<CommandInterceptor> interceptorChain = cache.getInterceptorChain();
		log.debug("Interceptor chain was: " + interceptorChain);
		int position = 0;
		// add interceptor before uses exact match, not instanceof match
		int invalidationPosition = 0;
		int entryWrappingPosition = 0;
		for (CommandInterceptor ci : interceptorChain) {
			if (ci instanceof InvalidationInterceptor) {
				invalidationPosition = position;
			}
			if (ci instanceof EntryWrappingInterceptor) {
				entryWrappingPosition = position;
			}
			position++;
		}
		boolean transactional = cache.getCacheConfiguration().transaction().transactionMode().isTransactional();
		if (transactional) {
			cache.removeInterceptor(invalidationPosition);
			TxInvalidationInterceptor txInvalidationInterceptor = new TxInvalidationInterceptor();
			cache.getComponentRegistry().registerComponent(txInvalidationInterceptor, TxInvalidationInterceptor.class);
			cache.addInterceptor(txInvalidationInterceptor, invalidationPosition);

			// Note that invalidation does *NOT* acquire locks; therefore, we have to start invalidating before
			// wrapping the entry, since if putFromLoad was invoked between wrap and beginInvalidatingKey, the invalidation
			// would not commit the entry removal (as during wrap the entry was not in cache)
			TxPutFromLoadInterceptor txPutFromLoadInterceptor = new TxPutFromLoadInterceptor(validator, cache.getName());
			cache.getComponentRegistry().registerComponent(txPutFromLoadInterceptor, TxPutFromLoadInterceptor.class);
			cache.addInterceptor(txPutFromLoadInterceptor, entryWrappingPosition);
		}
		else {
			cache.removeInterceptor(invalidationPosition);
			NonTxInvalidationInterceptor nonTxInvalidationInterceptor = new NonTxInvalidationInterceptor(validator);
			cache.getComponentRegistry().registerComponent(nonTxInvalidationInterceptor, NonTxInvalidationInterceptor.class);
			cache.addInterceptor(nonTxInvalidationInterceptor, invalidationPosition);

			NonTxPutFromLoadInterceptor nonTxPutFromLoadInterceptor = new NonTxPutFromLoadInterceptor(validator, cache.getName());
			cache.getComponentRegistry().registerComponent(nonTxPutFromLoadInterceptor, NonTxPutFromLoadInterceptor.class);
			cache.addInterceptor(nonTxPutFromLoadInterceptor, entryWrappingPosition);
			validator.nonTxPutFromLoadInterceptor = nonTxPutFromLoadInterceptor;
		}
		log.debug("New interceptor chain is: " + cache.getInterceptorChain());

		CacheCommandInitializer cacheCommandInitializer = cache.getComponentRegistry().getComponent(CacheCommandInitializer.class);
		cacheCommandInitializer.addPutFromLoadValidator(cache.getName(), validator);
	}
