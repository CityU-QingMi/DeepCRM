	public boolean compileExpression() {
		if (this.failedAttempts > FAILED_ATTEMPTS_THRESHOLD) {
			// Don't try again
			return false;
		}
		if (this.compiledAst == null) {
			synchronized (this.expression) {
				// Possibly compiled by another thread before this thread got into the sync block
				if (this.compiledAst != null) {
					return true;
				}
				SpelCompiler compiler = SpelCompiler.getCompiler(this.configuration.getCompilerClassLoader());
				this.compiledAst = compiler.compile(this.ast);
				if (this.compiledAst == null) {
					this.failedAttempts++;
				}
			}
		}
		return (this.compiledAst != null);
	}
