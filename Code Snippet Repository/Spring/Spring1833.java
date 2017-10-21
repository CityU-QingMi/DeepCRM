	private static CacheOperationInvoker.ThrowableWrapper rewriteCallStack(
			Throwable exception, String className, String methodName) {

		Throwable clone = cloneException(exception);
		if (clone == null) {
			return new CacheOperationInvoker.ThrowableWrapper(exception);
		}

		StackTraceElement[] callStack = new Exception().getStackTrace();
		StackTraceElement[] cachedCallStack = exception.getStackTrace();

		int index = findCommonAncestorIndex(callStack, className, methodName);
		int cachedIndex = findCommonAncestorIndex(cachedCallStack, className, methodName);
		if (index == -1 || cachedIndex == -1) {
			return new CacheOperationInvoker.ThrowableWrapper(exception); // Cannot find common ancestor
		}
		StackTraceElement[] result = new StackTraceElement[cachedIndex + callStack.length - index];
		System.arraycopy(cachedCallStack, 0, result, 0, cachedIndex);
		System.arraycopy(callStack, index, result, cachedIndex, callStack.length - index);

		clone.setStackTrace(result);
		return new CacheOperationInvoker.ThrowableWrapper(clone);
	}
