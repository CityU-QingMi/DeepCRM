	private static Class<?>[] resolveParameterTypes(Class<?> clazz, String methodName, String parameterTypeNames) {
		if (StringUtils.isBlank(parameterTypeNames)) {
			return EMPTY_CLASS_ARRAY;
		}

		// @formatter:off
		return Arrays.stream(parameterTypeNames.split(","))
				.map(typeName -> loadRequiredParameterType(clazz, methodName, typeName))
				.toArray(Class[]::new);
		// @formatter:on
	}
