	@Test
	@SuppressWarnings("")
	public void objectToCollection() throws Exception {
		List<List<String>> list = new ArrayList<>();
		list.add(Arrays.asList("9", "12"));
		list.add(Arrays.asList("37", "23"));
		conversionService.addConverterFactory(new StringToNumberConverterFactory());
		conversionService.addConverter(new ObjectToCollectionConverter(conversionService));
		conversionService.addConverter(new CollectionToObjectConverter(conversionService));
		TypeDescriptor sourceType = TypeDescriptor.forObject(list);
		TypeDescriptor targetType = new TypeDescriptor(getClass().getField("objectToCollection"));
		assertTrue(conversionService.canConvert(sourceType, targetType));
		List<List<List<Integer>>> result = (List<List<List<Integer>>>) conversionService.convert(list, sourceType, targetType);
		assertEquals((Integer) 9, result.get(0).get(0).get(0));
		assertEquals((Integer) 12, result.get(0).get(1).get(0));
		assertEquals((Integer) 37, result.get(1).get(0).get(0));
		assertEquals((Integer) 23, result.get(1).get(1).get(0));
	}
