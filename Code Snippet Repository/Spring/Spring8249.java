	public void assertValue(String content, @Nullable Object expectedValue) {
		Object actualValue = evaluateJsonPath(content);
		if ((actualValue instanceof List) && !(expectedValue instanceof List)) {
			@SuppressWarnings("rawtypes")
			List actualValueList = (List) actualValue;
			if (actualValueList.isEmpty()) {
				fail("No matching value at JSON path \"" + this.expression + "\"");
			}
			if (actualValueList.size() != 1) {
				fail("Got a list of values " + actualValue + " instead of the expected single value " + expectedValue);
			}
			actualValue = actualValueList.get(0);
		}
		else if (actualValue != null && expectedValue != null) {
			if (!actualValue.getClass().equals(expectedValue.getClass())) {
				actualValue = evaluateJsonPath(content, expectedValue.getClass());
			}
		}
		assertEquals("JSON path \"" + this.expression + "\"", expectedValue, actualValue);
	}
