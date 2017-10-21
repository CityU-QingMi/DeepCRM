	@Override
	public void evaluate() throws Throwable {
		for (int i = 0; i < this.repeat; i++) {
			if (this.repeat > 1 && logger.isInfoEnabled()) {
				logger.info(String.format("Repetition %d of test %s#%s()", (i + 1),
						this.testMethod.getDeclaringClass().getSimpleName(), this.testMethod.getName()));
			}
			this.next.evaluate();
		}
	}
