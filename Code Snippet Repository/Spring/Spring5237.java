	@Test
	@SuppressWarnings("")
	public void mapToMultiValueMap() throws Exception {
		DefaultConversionService.addDefaultConverters(conversionService);
		Map<String, Integer> source = new HashMap<>();
		source.put("a", 1);
		source.put("b", 2);
		TypeDescriptor targetType = new TypeDescriptor(getClass().getField("multiValueMapTarget"));

		MultiValueMap<String, String> converted = (MultiValueMap<String, String>) conversionService.convert(source, targetType);
		assertThat(converted.size(), equalTo(2));
		assertThat(converted.get("a"), equalTo(Arrays.asList("1")));
		assertThat(converted.get("b"), equalTo(Arrays.asList("2")));
	}
