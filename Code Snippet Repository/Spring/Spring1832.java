	@Override
	protected Object invoke(CacheOperationInvocationContext<CacheResultOperation> context,
			CacheOperationInvoker invoker) {

		CacheResultOperation operation = context.getOperation();
		Object cacheKey = generateKey(context);

		Cache cache = resolveCache(context);
		Cache exceptionCache = resolveExceptionCache(context);

		if (!operation.isAlwaysInvoked()) {
			Cache.ValueWrapper cachedValue = doGet(cache, cacheKey);
			if (cachedValue != null) {
				return cachedValue.get();
			}
			checkForCachedException(exceptionCache, cacheKey);
		}

		try {
			Object invocationResult = invoker.invoke();
			doPut(cache, cacheKey, invocationResult);
			return invocationResult;
		}
		catch (CacheOperationInvoker.ThrowableWrapper ex) {
			Throwable original = ex.getOriginal();
			cacheException(exceptionCache, operation.getExceptionTypeFilter(), cacheKey, original);
			throw ex;
		}
	}
