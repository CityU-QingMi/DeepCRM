	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		Type type = parameterContext.getParameter().getParameterizedType();
		if (!(type instanceof ParameterizedType)) {
			return false;
		}
		Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
		if (actualTypeArguments.length != 2) {
			return false;
		}
		return actualTypeArguments[0] == String.class && actualTypeArguments[1] == String.class;
	}
