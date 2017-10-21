	@Nullable
	public static Object getField(@Nullable Object targetObject, @Nullable Class<?> targetClass, String name) {
		Assert.isTrue(targetObject != null || targetClass != null,
			"Either targetObject or targetClass for the field must be specified");

		if (targetObject != null && springAopPresent) {
			targetObject = AopTestUtils.getUltimateTargetObject(targetObject);
		}
		if (targetClass == null) {
			targetClass = targetObject.getClass();
		}

		Field field = ReflectionUtils.findField(targetClass, name);
		if (field == null) {
			throw new IllegalArgumentException(String.format("Could not find field '%s' on %s or target class [%s]",
					name, safeToString(targetObject), targetClass));
		}

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Getting field '%s' from %s or target class [%s]", name,
					safeToString(targetObject), targetClass));
		}
		ReflectionUtils.makeAccessible(field);
		return ReflectionUtils.getField(field, targetObject);
	}
