	public static boolean isAnnotationDeclaredLocally(Class<? extends Annotation> annotationType, Class<?> clazz) {
		Assert.notNull(annotationType, "Annotation type must not be null");
		Assert.notNull(clazz, "Class must not be null");
		try {
			return (clazz.getDeclaredAnnotation(annotationType) != null);
		}
		catch (Throwable ex) {
			handleIntrospectionFailure(clazz, ex);
			return false;
		}
	}
