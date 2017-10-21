	@Test
	public void getDeclaredRepeatableAnnotationsDeclaredOnSuperclass() {
		final Class<?> clazz = SubMyRepeatableClass.class;

		// Java 8
		MyRepeatable[] array = clazz.getDeclaredAnnotationsByType(MyRepeatable.class);
		assertNotNull(array);
		assertThat(array.length, is(0));

		// Spring
		Set<MyRepeatable> set = getDeclaredRepeatableAnnotations(clazz, MyRepeatable.class, MyRepeatableContainer.class);
		assertNotNull(set);
		assertThat(set.size(), is(0));

		// When container type is omitted and therefore inferred from @Repeatable
		set = getDeclaredRepeatableAnnotations(clazz, MyRepeatable.class);
		assertNotNull(set);
		assertThat(set.size(), is(0));
	}
