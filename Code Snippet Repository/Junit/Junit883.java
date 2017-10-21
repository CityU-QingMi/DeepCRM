	@Test
	void getMethod() throws Exception {
		assertThat(ReflectionUtils.getMethod(Object.class, "hashCode")).contains(Object.class.getMethod("hashCode"));
		assertThat(ReflectionUtils.getMethod(String.class, "charAt", int.class))//
				.contains(String.class.getMethod("charAt", int.class));

		assertThat(ReflectionUtils.getMethod(Path.class, "subpath", int.class, int.class))//
				.contains(Path.class.getMethod("subpath", int.class, int.class));
		assertThat(ReflectionUtils.getMethod(String.class, "chars")).contains(String.class.getMethod("chars"));

		assertThat(ReflectionUtils.getMethod(String.class, "noSuchMethod")).isEmpty();
		assertThat(ReflectionUtils.getMethod(Object.class, "clone", int.class)).isEmpty();
	}
