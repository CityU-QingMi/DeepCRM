	private MethodExecutor findAccessorForMethod(String name, List<TypeDescriptor> argumentTypes,
			Object targetObject, EvaluationContext evaluationContext) throws SpelEvaluationException {

		List<MethodResolver> methodResolvers = evaluationContext.getMethodResolvers();
		for (MethodResolver methodResolver : methodResolvers) {
			try {
				MethodExecutor methodExecutor = methodResolver.resolve(
						evaluationContext, targetObject, name, argumentTypes);
				if (methodExecutor != null) {
					return methodExecutor;
				}
			}
			catch (AccessException ex) {
				throw new SpelEvaluationException(getStartPosition(), ex,
						SpelMessage.PROBLEM_LOCATING_METHOD, name, targetObject.getClass());
			}
		}

		throw new SpelEvaluationException(getStartPosition(), SpelMessage.METHOD_NOT_FOUND,
				FormatHelper.formatMethodForMessage(name, argumentTypes),
				FormatHelper.formatClassNameForMessage(
						targetObject instanceof Class ? ((Class<?>) targetObject) : targetObject.getClass()));
	}
