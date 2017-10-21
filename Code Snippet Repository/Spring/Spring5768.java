	private void checkCompile(ExpressionState expressionState) {
		this.interpretedCount++;
		SpelCompilerMode compilerMode = expressionState.getConfiguration().getCompilerMode();
		if (compilerMode != SpelCompilerMode.OFF) {
			if (compilerMode == SpelCompilerMode.IMMEDIATE) {
				if (this.interpretedCount > 1) {
					compileExpression();
				}
			}
			else {
				// compilerMode = SpelCompilerMode.MIXED
				if (this.interpretedCount > INTERPRETED_COUNT_THRESHOLD) {
					compileExpression();
				}
			}
		}
	}
