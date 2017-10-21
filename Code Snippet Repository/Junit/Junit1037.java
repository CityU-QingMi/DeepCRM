	@Test
	void classSourceWithFilePosition() {
		Class<?> testClass = getClass();
		FilePosition position = FilePosition.from(42, 23);
		ClassSource source = ClassSource.from(testClass, position);

		assertThat(source.getJavaClass()).isEqualTo(testClass);
		assertThat(source.getPosition()).isNotEmpty();
		assertThat(source.getPosition()).hasValue(position);
	}
