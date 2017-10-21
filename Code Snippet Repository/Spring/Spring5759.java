	@Override
	@Nullable
	public Object getValue() throws EvaluationException {
		if (this.compiledAst != null) {
			try {
				TypedValue contextRoot =
						(this.evaluationContext != null ? this.evaluationContext.getRootObject() : null);
				return this.compiledAst.getValue(
						(contextRoot != null ? contextRoot.getValue() : null), this.evaluationContext);
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

		ExpressionState expressionState = new ExpressionState(getEvaluationContext(), this.configuration);
		Object result = this.ast.getValue(expressionState);
		checkCompile(expressionState);
		return result;
	}
