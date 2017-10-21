	@SuppressWarnings("")
	static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable,
			Supplier<String> messageSupplier) {

		try {
			executable.execute();
		}
		catch (Throwable actualException) {
			if (expectedType.isInstance(actualException)) {
				return (T) actualException;
			}
			else {
				String message = buildPrefix(nullSafeGet(messageSupplier))
						+ format(expectedType, actualException.getClass(), "Unexpected exception type thrown");
				throw new AssertionFailedError(message, actualException);
			}
		}

		String message = buildPrefix(nullSafeGet(messageSupplier))
				+ String.format("Expected %s to be thrown, but nothing was thrown.", getCanonicalName(expectedType));
		throw new AssertionFailedError(message);
	}
