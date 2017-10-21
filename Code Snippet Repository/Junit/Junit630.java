	public static Optional<Method> loadMethod(String fullyQualifiedMethodName) {
		Preconditions.notBlank(fullyQualifiedMethodName, "Fully qualified method name must not be null or blank");

		String fqmn = fullyQualifiedMethodName.trim();
		Matcher matcher = FULLY_QUALIFIED_METHOD_NAME_PATTERN.matcher(fqmn);

		Preconditions.condition(matcher.matches(),
			() -> String.format("Fully qualified method name [%s] does not match pattern [%s]", fqmn,
				FULLY_QUALIFIED_METHOD_NAME_PATTERN));

		String className = matcher.group(1);
		String methodName = matcher.group(2);
		// Note: group #3 includes the parameter types enclosed in parentheses;
		// group #4 contains the actual parameter types.
		String parameterTypeNames = matcher.group(4);

		Optional<Class<?>> classOptional = loadClass(className);
		if (classOptional.isPresent()) {
			try {
				return findMethod(classOptional.get(), methodName.trim(), parameterTypeNames);
			}
			catch (Exception ex) {
				/* ignore */
			}
		}

		return Optional.empty();
	}
