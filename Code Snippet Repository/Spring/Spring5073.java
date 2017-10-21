	@Test
	public void getRepeatableAnnotationsDeclaredOnClass() {
		final List<String> expectedValuesJava = asList("A", "B", "C");
		final List<String> expectedValuesSpring = asList("A", "B", "C", "meta1");

		// Java 8
		MyRepeatable[] array = MyRepeatableClass.class.getAnnotationsByType(MyRepeatable.class);
		assertNotNull(array);
		List<String> values = stream(array).map(MyRepeatable::value).collect(toList());
		assertThat(values, is(expectedValuesJava));

		// Spring
		Set<MyRepeatable> set = getRepeatableAnnotations(MyRepeatableClass.class, MyRepeatable.class, MyRepeatableContainer.class);
		assertNotNull(set);
		values = set.stream().map(MyRepeatable::value).collect(toList());
		assertThat(values, is(expectedValuesSpring));

		// When container type is omitted and therefore inferred from @Repeatable
		set = getRepeatableAnnotations(MyRepeatableClass.class, MyRepeatable.class);
		assertNotNull(set);
		values = set.stream().map(MyRepeatable::value).collect(toList());
		assertThat(values, is(expectedValuesSpring));
	}
