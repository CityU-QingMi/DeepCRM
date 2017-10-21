	@Override
	@Nullable
	public Object getValue(EvaluationContext context) throws EvaluationException {
		Assert.notNull(context, "EvaluationContext is required");

		if (this.compiledAst != null) {
			try {
				TypedValue contextRoot = context.getRootObject();
				return this.compiledAst.getValue(contextRoot.getValue(), context);
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

		ExpressionState expressionState = new ExpressionState(context, this.configuration);
		Object result = this.ast.getValue(expressionState);
		checkCompile(expressionState);
		return result;
	}
