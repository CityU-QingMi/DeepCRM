	@Test
	void findMethodByParameterTypes() throws Exception {
		assertThat(findMethod(Object.class, "noSuchMethod")).isEmpty();
		assertThat(findMethod(String.class, "noSuchMethod")).isEmpty();

		assertThat(findMethod(String.class, "chars")).contains(String.class.getMethod("chars"));
		assertThat(findMethod(Files.class, "copy", Path.class, OutputStream.class))//
				.contains(Files.class.getMethod("copy", Path.class, OutputStream.class));

		assertThat(findMethod(MethodShadowingChild.class, "method1", String.class))//
				.contains(MethodShadowingChild.class.getMethod("method1", String.class));
	}
