	static void assertExpectedAndActualValues(AssertionFailedError ex, Object expected, Object actual)
			throws AssertionError {
		if (!wrapsEqualValue(ex.getExpected(), expected)) {
			throw new AssertionError("Expected value in AssertionFailedError should equal ["
					+ ValueWrapper.create(expected) + "], but was [" + ex.getExpected() + "].");
		}
		if (!wrapsEqualValue(ex.getActual(), actual)) {
			throw new AssertionError("Actual value in AssertionFailedError should equal [" + ValueWrapper.create(actual)
					+ "], but was [" + ex.getActual() + "].");
		}
	}
