	@Override
	public void evaluate() throws Throwable {
		List<Throwable> errors = new ArrayList<>();
		try {
			this.next.evaluate();
		}
		catch (Throwable ex) {
			errors.add(ex);
		}

		try {
			this.testContextManager.afterTestClass();
		}
		catch (Throwable ex) {
			errors.add(ex);
		}

		MultipleFailureException.assertEmpty(errors);
	}
