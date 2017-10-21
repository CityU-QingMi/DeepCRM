	@Test
	@SuppressWarnings("")
	public void multiValueMapToMultiValueMap() throws Exception {
		DefaultConversionService.addDefaultConverters(conversionService);
		MultiValueMap<String, Integer> source = new LinkedMultiValueMap<>();
		source.put("a", Arrays.asList(1, 2, 3));
		source.put("b", Arrays.asList(4, 5, 6));
		TypeDescriptor targetType = new TypeDescriptor(getClass().getField("multiValueMapTarget"));

		MultiValueMap<String, String> converted = (MultiValueMap<String, String>) conversionService.convert(source, targetType);
		assertThat(converted.size(), equalTo(2));
		assertThat(converted.get("a"), equalTo(Arrays.asList("1", "2", "3")));
		assertThat(converted.get("b"), equalTo(Arrays.asList("4", "5", "6")));
	}
