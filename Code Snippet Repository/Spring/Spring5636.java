	private ConstructorExecutor findExecutorForConstructor(String typeName,
			List<TypeDescriptor> argumentTypes, ExpressionState state) throws SpelEvaluationException {

		EvaluationContext evalContext = state.getEvaluationContext();
		List<ConstructorResolver> ctorResolvers = evalContext.getConstructorResolvers();
		for (ConstructorResolver ctorResolver : ctorResolvers) {
			try {
				ConstructorExecutor ce = ctorResolver.resolve(state.getEvaluationContext(), typeName, argumentTypes);
				if (ce != null) {
					return ce;
				}
			}
			catch (AccessException ex) {
				throw new SpelEvaluationException(getStartPosition(), ex,
						SpelMessage.CONSTRUCTOR_INVOCATION_PROBLEM, typeName,
						FormatHelper.formatMethodForMessage("", argumentTypes));
			}
		}
		throw new SpelEvaluationException(getStartPosition(), SpelMessage.CONSTRUCTOR_NOT_FOUND, typeName,
				FormatHelper.formatMethodForMessage("", argumentTypes));
	}
