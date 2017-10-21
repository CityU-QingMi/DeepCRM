	@Test
	void findNestedClasses() {
		// @formatter:off
		assertThat(ReflectionUtils.findNestedClasses(Object.class, clazz -> true)).isEmpty();

		assertThat(ReflectionUtils.findNestedClasses(ClassWithNestedClasses.class, clazz -> true))
			.containsOnly(Nested1.class, Nested2.class, Nested3.class);

		assertThat(ReflectionUtils.findNestedClasses(ClassWithNestedClasses.class, clazz -> clazz.getName().contains("1")))
			.containsExactly(Nested1.class);

		assertThat(ReflectionUtils.findNestedClasses(ClassWithNestedClasses.class, ReflectionUtils::isStatic))
			.containsExactly(Nested3.class);

		assertThat(ReflectionUtils.findNestedClasses(ClassExtendingClassWithNestedClasses.class, clazz -> true))
			.containsOnly(Nested1.class, Nested2.class, Nested3.class, Nested4.class, Nested5.class);
		// @formatter:on
	}
