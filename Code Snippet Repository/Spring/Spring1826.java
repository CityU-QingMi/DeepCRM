	@Override
	protected Object invoke(CacheOperationInvocationContext<CachePutOperation> context,
			CacheOperationInvoker invoker) {

		CacheKeyInvocationContext<CachePut> invocationContext = createCacheKeyInvocationContext(context);
		CachePutOperation operation = context.getOperation();

		boolean earlyPut = operation.isEarlyPut();
		Object value = invocationContext.getValueParameter().getValue();

		if (earlyPut) {
			cacheValue(context, value);
		}

		try {
			Object result = invoker.invoke();
			if (!earlyPut) {
				cacheValue(context, value);
			}
			return result;
		}
		catch (CacheOperationInvoker.ThrowableWrapper ex) {
			Throwable original = ex.getOriginal();
			if (!earlyPut && operation.getExceptionTypeFilter().match(original.getClass())) {
				cacheValue(context, value);
			}
			throw ex;
		}
	}
