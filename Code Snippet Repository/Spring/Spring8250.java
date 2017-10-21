	public void doesNotExist(String content) {
		Object value;
		try {
			value = evaluateJsonPath(content);
		}
		catch (AssertionError ex) {
			return;
		}
		String reason = failureReason("no value", value);
		if (pathIsIndefinite() && value instanceof List) {
			assertTrue(reason, ((List<?>) value).isEmpty());
		}
		else {
			assertTrue(reason, (value == null));
		}
	}
