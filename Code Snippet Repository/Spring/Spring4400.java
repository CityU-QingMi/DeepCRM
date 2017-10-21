	@SuppressWarnings("")
	@Nullable
	private static <A extends Annotation> A findAnnotation(
			Class<?> clazz, @Nullable Class<A> annotationType, boolean synthesize) {

		Assert.notNull(clazz, "Class must not be null");
		if (annotationType == null) {
			return null;
		}

		AnnotationCacheKey cacheKey = new AnnotationCacheKey(clazz, annotationType);
		A result = (A) findAnnotationCache.get(cacheKey);
		if (result == null) {
			result = findAnnotation(clazz, annotationType, new HashSet<>());
			if (result != null && synthesize) {
				result = synthesizeAnnotation(result, clazz);
				findAnnotationCache.put(cacheKey, result);
			}
		}
		return result;
	}
