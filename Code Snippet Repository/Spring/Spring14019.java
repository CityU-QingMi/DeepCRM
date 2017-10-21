	@SuppressWarnings("")
	private static Map<ClassLoader, WebApplicationContext> getCurrentContextPerThreadFromContextLoader() {
		try {
			Field field = ContextLoader.class.getDeclaredField("currentContextPerThread");
			field.setAccessible(true);
			return (Map<ClassLoader, WebApplicationContext>) field.get(null);
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}
