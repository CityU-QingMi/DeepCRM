	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		// Exact match?
		if (parameterContext.getParameter().getType() == Long.class) {
			return true;
		}

		Type typeInMethod = parameterContext.getParameter().getParameterizedType();

		// Type variables in parameterized class
		for (TypeVariable<?> typeVariable : parameterContext.getDeclaringExecutable().getDeclaringClass().getTypeParameters()) {
			boolean namesMatch = typeInMethod.getTypeName().equals(typeVariable.getName());
			boolean typesAreCompatible = typeVariable.getBounds().length == 1 && //
					typeVariable.getBounds()[0] instanceof Class && //
					((Class<?>) typeVariable.getBounds()[0]).isAssignableFrom(Long.class);

			if (namesMatch && typesAreCompatible) {
				return true;
			}
		}

		return false;

	}
