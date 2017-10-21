	@SuppressWarnings("")
	@Override
	@Nullable
	public <T> T getValue(Object rootObject, @Nullable Class<T> expectedResultType) throws EvaluationException {
		if (this.compiledAst != null) {
			try {
				Object result = this.compiledAst.getValue(rootObject, null);
				if (expectedResultType == null) {
					return (T)result;
				}
				else {
					return ExpressionUtils.convertTypedValue(
							getEvaluationContext(), new TypedValue(result), expectedResultType);
				}
			}
			catch (Throwable ex) {
				// If running in mixed mode, revert to interpreted
				if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
					this.interpretedCount = 0;
					this.compiledAst = null;
				}
				else {
					// Running in SpelCompilerMode.immediate mode - propagate exception to caller
					throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION);
				}
			}
		}

		ExpressionState expressionState =
				new ExpressionState(getEvaluationContext(), toTypedValue(rootObject), this.configuration);
		TypedValue typedResultValue = this.ast.getTypedValue(expressionState);
		checkCompile(expressionState);
		return ExpressionUtils.convertTypedValue(
				expressionState.getEvaluationContext(), typedResultValue, expectedResultType);
	}
