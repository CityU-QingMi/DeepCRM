	@Override
	public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
		TypedValue value = state.lookupVariable(this.name);
		if (value == TypedValue.NULL) {
			throw new SpelEvaluationException(getStartPosition(), SpelMessage.FUNCTION_NOT_DEFINED, this.name);
		}

		// Two possibilities: a lambda function or a Java static method registered as a function
		if (!(value.getValue() instanceof Method)) {
			throw new SpelEvaluationException(
					SpelMessage.FUNCTION_REFERENCE_CANNOT_BE_INVOKED, this.name, value.getClass());
		}

		try {
			return executeFunctionJLRMethod(state, (Method) value.getValue());
		}
		catch (SpelEvaluationException ex) {
			ex.setPosition(getStartPosition());
			throw ex;
		}
	}
