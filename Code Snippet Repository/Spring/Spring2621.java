	public static boolean isValidExpression(@Nullable String expression) {
		if (expression == null) {
			return false;
		}
		String[] fields = StringUtils.tokenizeToStringArray(expression, " ");
		if (!areValidCronFields(fields)) {
			return false;
		}
		try {
			new CronSequenceGenerator(expression, fields);
			return true;
		}
		catch (IllegalArgumentException ex) {
			return false;
		}
	}
