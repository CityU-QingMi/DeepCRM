	@Override
	protected Object invoke(CacheOperationInvocationContext<CacheRemoveOperation> context,
			CacheOperationInvoker invoker) {
		CacheRemoveOperation operation = context.getOperation();

		final boolean earlyRemove = operation.isEarlyRemove();

		if (earlyRemove) {
			removeValue(context);
		}

		try {
			Object result = invoker.invoke();
			if (!earlyRemove) {
				removeValue(context);
			}
			return result;
		}
		catch (CacheOperationInvoker.ThrowableWrapper t) {
			Throwable ex = t.getOriginal();
			if (!earlyRemove && operation.getExceptionTypeFilter().match(ex.getClass())) {
				removeValue(context);
			}
			throw t;
		}
	}
