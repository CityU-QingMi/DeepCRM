	private Stream<Object[]> provideArguments(InputStream inputStream, String lineSeparator, char delimiter) {
		String expectedResource = "foo/bar";
		CsvFileSource annotation = annotation("ISO-8859-1", lineSeparator, delimiter, expectedResource);

		CsvFileArgumentsProvider provider = new CsvFileArgumentsProvider((testClass, resource) -> {
			assertThat(resource).isEqualTo(expectedResource);
			return inputStream;
		});
		return provide(provider, annotation);
	}
