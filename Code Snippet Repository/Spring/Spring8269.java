	@Override
	public void verify() {
		if (this.expectations.isEmpty()) {
			return;
		}
		int count = 0;
		for (RequestExpectation expectation : this.expectations) {
			if (!expectation.isSatisfied()) {
				count++;
			}
		}
		if (count > 0) {
			String message = "Further request(s) expected leaving " + count + " unsatisfied expectation(s).\n";
			throw new AssertionError(message + getRequestDetails());
		}
	}
