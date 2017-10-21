	@Nullable
	public static Object getValue(@Nullable Annotation annotation, @Nullable String attributeName) {
		if (annotation == null || !StringUtils.hasText(attributeName)) {
			return null;
		}
		try {
			Method method = annotation.annotationType().getDeclaredMethod(attributeName);
			ReflectionUtils.makeAccessible(method);
			return method.invoke(annotation);
		}
		catch (InvocationTargetException ex) {
			rethrowAnnotationConfigurationException(ex.getTargetException());
			throw new IllegalStateException(
					"Could not obtain value for annotation attribute '" + attributeName + "' in " + annotation, ex);
		}
		catch (Throwable ex) {
			handleIntrospectionFailure(annotation.getClass(), ex);
			return null;
		}
	}
