	@Override
	protected Object invoke(CacheOperationInvocationContext<CacheRemoveAllOperation> context,
			CacheOperationInvoker invoker) {

		CacheRemoveAllOperation operation = context.getOperation();

		boolean earlyRemove = operation.isEarlyRemove();

		if (earlyRemove) {
			removeAll(context);
		}

		try {
			Object result = invoker.invoke();
			if (!earlyRemove) {
				removeAll(context);
			}
			return result;
		}
		catch (CacheOperationInvoker.ThrowableWrapper ex) {
			Throwable original = ex.getOriginal();
			if (!earlyRemove && operation.getExceptionTypeFilter().match(original.getClass())) {
				removeAll(context);
			}
			throw ex;
		}
	}
