	@SuppressWarnings("")
	@Nullable
	static <A extends Annotation> A[] synthesizeAnnotationArray(@Nullable Map<String, Object>[] maps, Class<A> annotationType) {
		Assert.notNull(annotationType, "'annotationType' must not be null");
		if (maps == null) {
			return null;
		}

		A[] synthesized = (A[]) Array.newInstance(annotationType, maps.length);
		for (int i = 0; i < maps.length; i++) {
			synthesized[i] = synthesizeAnnotation(maps[i], annotationType, null);
		}
		return synthesized;
	}
