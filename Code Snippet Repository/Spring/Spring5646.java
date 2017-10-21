	private TypedValue executeFunctionJLRMethod(ExpressionState state, Method method) throws EvaluationException {
		this.method = null;
		Object[] functionArgs = getArguments(state);

		if (!method.isVarArgs() && method.getParameterCount() != functionArgs.length) {
			throw new SpelEvaluationException(SpelMessage.INCORRECT_NUMBER_OF_ARGUMENTS_TO_FUNCTION,
					functionArgs.length, method.getParameterCount());
		}
		// Only static methods can be called in this way
		if (!Modifier.isStatic(method.getModifiers())) {
			throw new SpelEvaluationException(getStartPosition(),
					SpelMessage.FUNCTION_MUST_BE_STATIC, ClassUtils.getQualifiedMethodName(method), this.name);
		}

		// Convert arguments if necessary and remap them for varargs if required
		TypeConverter converter = state.getEvaluationContext().getTypeConverter();
		argumentConversionOccurred = ReflectionHelper.convertAllArguments(converter, functionArgs, method);
		if (method.isVarArgs()) {
			functionArgs = ReflectionHelper.setupArgumentsForVarargsInvocation(
					method.getParameterTypes(), functionArgs);
		}

		try {
			ReflectionUtils.makeAccessible(method);
			Object result = method.invoke(method.getClass(), functionArgs);
			if (!argumentConversionOccurred) {
				this.method = method;
				this.exitTypeDescriptor = CodeFlow.toDescriptor(method.getReturnType());
			}
			return new TypedValue(result, new TypeDescriptor(new MethodParameter(method, -1)).narrow(result));
		}
		catch (Exception ex) {
			throw new SpelEvaluationException(getStartPosition(), ex, SpelMessage.EXCEPTION_DURING_FUNCTION_CALL,
					this.name, ex.getMessage());
		}
	}
