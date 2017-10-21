	@Test
	@SuppressWarnings("")
	public void convertCollectionToObjectWithCustomConverter() throws Exception {
		List<String> source = new ArrayList<>();
		source.add("A");
		source.add("B");
		conversionService.addConverter(new Converter<List, ListWrapper>() {
			@Override
			public ListWrapper convert(List source) {
				return new ListWrapper(source);
			}
		});
		ListWrapper result = conversionService.convert(source, ListWrapper.class);
		assertSame(source, result.getList());
	}
