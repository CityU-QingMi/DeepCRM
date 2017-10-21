	public static void setField(@Nullable Object targetObject, @Nullable Class<?> targetClass,
			@Nullable String name, @Nullable Object value, @Nullable Class<?> type) {

		Assert.isTrue(targetObject != null || targetClass != null,
				"Either targetObject or targetClass for the field must be specified");

		if (targetObject != null && springAopPresent) {
			targetObject = AopTestUtils.getUltimateTargetObject(targetObject);
		}
		if (targetClass == null) {
			targetClass = targetObject.getClass();
		}

		Field field = ReflectionUtils.findField(targetClass, name, type);
		if (field == null) {
			throw new IllegalArgumentException(String.format(
					"Could not find field '%s' of type [%s] on %s or target class [%s]", name, type,
					safeToString(targetObject), targetClass));
		}

		if (logger.isDebugEnabled()) {
			logger.debug(String.format(
					"Setting field '%s' of type [%s] on %s or target class [%s] to value [%s]", name, type,
					safeToString(targetObject), targetClass, value));
		}
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, targetObject, value);
	}
