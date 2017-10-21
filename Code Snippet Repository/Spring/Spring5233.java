	@Test
	public void collectionMapNotGenericTargetCollectionToObjectInteraction() throws Exception {
		Map<String, List<String>> map = new HashMap<>();
		map.put("1", Arrays.asList("9", "12"));
		map.put("2", Arrays.asList("37", "23"));
		conversionService.addConverter(new CollectionToCollectionConverter(conversionService));
		conversionService.addConverter(new CollectionToObjectConverter(conversionService));

		assertTrue(conversionService.canConvert(Map.class, Map.class));
		assertSame(map, conversionService.convert(map, Map.class));
	}
