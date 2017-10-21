	private <E extends Enum<E>> Stream<Object[]> provideArguments(Class<E> enumClass, Mode mode, String... names) {
		EnumSource annotation = mock(EnumSource.class);
		when(annotation.value()).thenAnswer(invocation -> enumClass);
		when(annotation.mode()).thenAnswer(invocation -> mode);
		when(annotation.names()).thenAnswer(invocation -> names);
		when(annotation.toString()).thenReturn(String.format("@EnumSource(value=%s.class, mode=%s, names=%s)",
			enumClass.getSimpleName(), mode, Arrays.toString(names)));

		EnumArgumentsProvider provider = new EnumArgumentsProvider();
		provider.accept(annotation);
		return provider.provideArguments(null).map(Arguments::get);
	}
