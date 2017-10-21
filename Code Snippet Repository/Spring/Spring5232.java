	@Test
	public void collectionMapSourceTarget() throws Exception {
		Map<String, List<String>> map = new HashMap<>();
		map.put("1", Arrays.asList("9", "12"));
		map.put("2", Arrays.asList("37", "23"));
		TypeDescriptor sourceType = new TypeDescriptor(getClass().getField("sourceCollectionMapTarget"));
		TypeDescriptor targetType = new TypeDescriptor(getClass().getField("collectionMapTarget"));

		assertFalse(conversionService.canConvert(sourceType, targetType));
		try {
			conversionService.convert(map, sourceType, targetType);
			fail("Should have failed");
		}
		catch (ConverterNotFoundException ex) {
			// expected
		}

		conversionService.addConverter(new CollectionToCollectionConverter(conversionService));
		conversionService.addConverterFactory(new StringToNumberConverterFactory());
		assertTrue(conversionService.canConvert(sourceType, targetType));
		@SuppressWarnings("unchecked")
		Map<Integer, List<Integer>> result = (Map<Integer, List<Integer>>) conversionService.convert(map, sourceType, targetType);
		assertFalse(map.equals(result));
		assertEquals(Arrays.asList(9, 12), result.get(1));
		assertEquals(Arrays.asList(37, 23), result.get(2));
	}
