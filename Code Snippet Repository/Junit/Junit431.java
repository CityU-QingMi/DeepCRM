	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		Class<?> type = parameterContext.getParameter().getType();
		if (type == Number.class) {
			return 42;
		}
		try {
			return type.getMethod("valueOf", String.class).invoke(null, "123");
		}
		catch (Exception e) {
			throw new AssertionError("Could not resolve number type: " + type, e);
		}
	}
