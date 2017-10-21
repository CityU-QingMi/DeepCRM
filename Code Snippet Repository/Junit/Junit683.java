	public static MethodSelector selectMethod(String fullyQualifiedMethodName) throws PreconditionViolationException {
		Preconditions.notBlank(fullyQualifiedMethodName, "fullyQualifiedMethodName must not be null or blank");

		Matcher matcher = fullyQualifiedMethodNamePattern.matcher(fullyQualifiedMethodName);
		Preconditions.condition(matcher.matches(),
			fullyQualifiedMethodName + " is not a valid fully qualified method name");

		String className = matcher.group(1);
		String methodName = matcher.group(2);
		String methodParameters = matcher.group(3);
		if (StringUtils.isNotBlank(methodParameters)) {
			return selectMethod(className, methodName, methodParameters);
		}
		return selectMethod(className, methodName);
	}
