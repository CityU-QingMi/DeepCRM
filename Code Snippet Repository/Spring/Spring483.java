	@SuppressWarnings("")
	@Nullable
	public static <T> Constructor<T> findPrimaryConstructor(Class<T> clazz) {
		Assert.notNull(clazz, "Class must not be null");
		if (KotlinDetector.isKotlinType(clazz)) {
			Constructor<T> kotlinPrimaryConstructor = KotlinDelegate.findPrimaryConstructor(clazz);
			if (kotlinPrimaryConstructor != null) {
				return kotlinPrimaryConstructor;
			}
		}
		return null;
	}
