	@Nullable
	private Object evaluateJsonPath(String content) {
		String message = "No value at JSON path \"" + this.expression + "\"";
		try {
			return this.jsonPath.read(content);
		}
		catch (Throwable ex) {
			throw new AssertionError(message, ex);
		}
	}
