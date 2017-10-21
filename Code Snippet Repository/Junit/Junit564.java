	private String formatSafely(String pattern, Object[] arguments) {
		try {
			return MessageFormat.format(pattern, arguments);
		}
		catch (IllegalArgumentException ex) {
			String message = "The naming pattern defined for the parameterized tests is invalid. "
					+ "The nested exception contains more details.";
			throw new JUnitException(message, ex);
		}
	}
