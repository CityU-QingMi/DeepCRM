	@Test
	void loadMethod() {
		assertThat(ReflectionUtils.loadMethod(PublicClass.class.getName() + "#publicMethod")).isPresent();
		assertThat(ReflectionUtils.loadMethod(PrivateClass.class.getName() + "#privateMethod")).isPresent();
		assertThat(ReflectionUtils.loadMethod("  " + PrivateClass.class.getName() + "#privateMethod  ")).isPresent();

		assertThat(ReflectionUtils.loadMethod("org.example.NonexistentClass#nonexistentMethod")).isEmpty();
		assertThat(ReflectionUtils.loadMethod(PublicClass.class.getName() + "#nonexistentMethod")).isEmpty();

		// missing java.lang.String parameter type
		assertThat(ReflectionUtils.loadMethod("java.lang.String#equalsIgnoreCase")).isEmpty();
	}
