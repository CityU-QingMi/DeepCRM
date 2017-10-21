	@Nullable
	private MethodExecutor getCachedExecutor(EvaluationContext evaluationContext, Object value,
			@Nullable TypeDescriptor target, List<TypeDescriptor> argumentTypes) {

		List<MethodResolver> methodResolvers = evaluationContext.getMethodResolvers();
		if (methodResolvers.size() != 1 ||
				!(methodResolvers.get(0) instanceof ReflectiveMethodResolver)) {
			// Not a default ReflectiveMethodResolver - don't know whether caching is valid
			return null;
		}

		CachedMethodExecutor executorToCheck = this.cachedExecutor;
		if (executorToCheck != null && executorToCheck.isSuitable(value, target, argumentTypes)) {
			return executorToCheck.get();
		}
		this.cachedExecutor = null;
		return null;
	}
