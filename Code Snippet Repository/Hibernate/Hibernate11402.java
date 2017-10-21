	public static PutFromLoadValidator removeFromCache(AdvancedCache cache) {
		cache.removeInterceptor(TxPutFromLoadInterceptor.class);
		cache.removeInterceptor(NonTxPutFromLoadInterceptor.class);
		for (Object i : cache.getInterceptorChain()) {
			if (i instanceof NonTxInvalidationInterceptor) {
				InvalidationInterceptor invalidationInterceptor = new InvalidationInterceptor();
				cache.getComponentRegistry().registerComponent(invalidationInterceptor, InvalidationInterceptor.class);
				cache.addInterceptorBefore(invalidationInterceptor, NonTxInvalidationInterceptor.class);
				cache.removeInterceptor(NonTxInvalidationInterceptor.class);
				break;
			}
			else if (i instanceof TxInvalidationInterceptor) {
				InvalidationInterceptor invalidationInterceptor = new InvalidationInterceptor();
				cache.getComponentRegistry().registerComponent(invalidationInterceptor, InvalidationInterceptor.class);
				cache.addInterceptorBefore(invalidationInterceptor, TxInvalidationInterceptor.class);
				cache.removeInterceptor(TxInvalidationInterceptor.class);
				break;
			}
		}
		CacheCommandInitializer cci = cache.getComponentRegistry().getComponent(CacheCommandInitializer.class);
		return cci.removePutFromLoadValidator(cache.getName());
	}
