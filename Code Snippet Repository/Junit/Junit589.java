	private Stream<Object[]> provideArguments(String[] strings, int[] ints, long[] longs, double[] doubles) {
		ValueSource annotation = mock(ValueSource.class);
		when(annotation.strings()).thenReturn(strings);
		when(annotation.ints()).thenReturn(ints);
		when(annotation.longs()).thenReturn(longs);
		when(annotation.doubles()).thenReturn(doubles);

		ValueArgumentsProvider provider = new ValueArgumentsProvider();
		provider.accept(annotation);
		return provider.provideArguments(null).map(Arguments::get);
	}
