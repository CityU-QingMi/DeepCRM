	@Nullable
	private Object assertExistsAndReturn(String content) {
		Object value = evaluateJsonPath(content);
		String reason = "No value at JSON path \"" + this.expression + "\"";
		assertTrue(reason, value != null);
		if (pathIsIndefinite() && value instanceof List) {
			assertTrue(reason, !((List<?>) value).isEmpty());
		}
		return value;
	}
