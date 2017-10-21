	Optional<Method> findMethod(String methodSpecPart, Class<?> clazz) {
		Matcher matcher = METHOD_PATTERN.matcher(methodSpecPart);

		Preconditions.condition(matcher.matches(),
			() -> String.format("Method [%s] does not match pattern [%s]", methodSpecPart, METHOD_PATTERN));

		String methodName = matcher.group(1);
		String parameterTypeNames = matcher.group(2);
		return ReflectionUtils.findMethod(clazz, methodName, parameterTypeNames);
	}
