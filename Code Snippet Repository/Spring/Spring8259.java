	public static void invokeSetterMethod(Object target, String name, @Nullable Object value, @Nullable Class<?> type) {
		Assert.notNull(target, "Target object must not be null");
		Assert.hasText(name, "Method name must not be empty");
		Class<?>[] paramTypes = (type != null ? new Class<?>[] {type} : null);

		String setterMethodName = name;
		if (!name.startsWith(SETTER_PREFIX)) {
			setterMethodName = SETTER_PREFIX + StringUtils.capitalize(name);
		}

		Method method = ReflectionUtils.findMethod(target.getClass(), setterMethodName, paramTypes);
		if (method == null && !setterMethodName.equals(name)) {
			setterMethodName = name;
			method = ReflectionUtils.findMethod(target.getClass(), setterMethodName, paramTypes);
		}
		if (method == null) {
			throw new IllegalArgumentException(String.format(
					"Could not find setter method '%s' on %s with parameter type [%s]", setterMethodName,
					safeToString(target), type));
		}

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Invoking setter method '%s' on %s with value [%s]", setterMethodName,
					safeToString(target), value));
		}

		ReflectionUtils.makeAccessible(method);
		ReflectionUtils.invokeMethod(method, target, value);
	}
