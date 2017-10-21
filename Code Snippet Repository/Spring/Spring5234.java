	@Test
	public void emptyMapDifferentTargetImplType() throws Exception {
		Map<String, String> map = new HashMap<>();
		TypeDescriptor sourceType = TypeDescriptor.forObject(map);
		TypeDescriptor targetType = new TypeDescriptor(getClass().getField("emptyMapDifferentTarget"));

		assertTrue(conversionService.canConvert(sourceType, targetType));
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, String> result = (LinkedHashMap<String, String>) conversionService.convert(map, sourceType, targetType);
		assertEquals(map, result);
		assertEquals(LinkedHashMap.class, result.getClass());
	}
