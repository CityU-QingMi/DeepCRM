	private void replaceCommonInterceptors() {
		CacheMode cacheMode = cache.getCacheConfiguration().clustering().cacheMode();
		if (!cacheMode.isReplicated() && !cacheMode.isDistributed()) {
			return;
		}

		LockingInterceptor lockingInterceptor = new LockingInterceptor();
		cache.getComponentRegistry().registerComponent(lockingInterceptor, LockingInterceptor.class);
		if (!cache.addInterceptorBefore(lockingInterceptor, NonTransactionalLockingInterceptor.class)) {
			throw new IllegalStateException("Misconfigured cache, interceptor chain is " + cache.getInterceptorChain());
		}
		cache.removeInterceptor(NonTransactionalLockingInterceptor.class);

		UnorderedDistributionInterceptor distributionInterceptor = new UnorderedDistributionInterceptor();
		cache.getComponentRegistry().registerComponent(distributionInterceptor, UnorderedDistributionInterceptor.class);
		if (!cache.addInterceptorBefore(distributionInterceptor, NonTxDistributionInterceptor.class)) {
			throw new IllegalStateException("Misconfigured cache, interceptor chain is " + cache.getInterceptorChain());
		}
		cache.removeInterceptor(NonTxDistributionInterceptor.class);

		EntryWrappingInterceptor ewi = cache.getComponentRegistry().getComponent(EntryWrappingInterceptor.class);
		try {
			Field isUsingLockDelegation = EntryWrappingInterceptor.class.getDeclaredField("isUsingLockDelegation");
			isUsingLockDelegation.setAccessible(true);
			isUsingLockDelegation.set(ewi, false);
		}
		catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
