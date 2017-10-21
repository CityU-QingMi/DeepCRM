	@Override
	public void evaluate() throws Throwable {
		Throwable testException = null;
		List<Throwable> errors = new ArrayList<>();
		try {
			this.next.evaluate();
		}
		catch (Throwable ex) {
			testException = ex;
			errors.add(ex);
		}

		try {
			this.testContextManager.afterTestExecution(this.testInstance, this.testMethod, testException);
		}
		catch (Throwable ex) {
			errors.add(ex);
		}

		MultipleFailureException.assertEmpty(errors);
	}
