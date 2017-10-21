	@Test
	public void convertStringToCustomCharArray() throws Exception {
		conversionService.addConverter(new Converter<String, char[]>() {
			@Override
			public char[] convert(String source) {
				return source.toCharArray();
			}
		});
		char[] converted = conversionService.convert("abc", char[].class);
		assertThat(converted, equalTo(new char[] {'a', 'b', 'c'}));
	}
