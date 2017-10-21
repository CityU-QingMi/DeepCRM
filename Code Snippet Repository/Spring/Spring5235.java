	@Test
	public void noDefaultConstructorCopyNotRequired() throws Exception {
		// SPR-9284
		NoDefaultConstructorMap<String, Integer> map = new NoDefaultConstructorMap<>(
				Collections.<String, Integer>singletonMap("1", 1));
		TypeDescriptor sourceType = TypeDescriptor.map(NoDefaultConstructorMap.class,
				TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Integer.class));
		TypeDescriptor targetType = TypeDescriptor.map(NoDefaultConstructorMap.class,
				TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Integer.class));

		assertTrue(conversionService.canConvert(sourceType, targetType));
		@SuppressWarnings("unchecked")
		Map<String, Integer> result = (Map<String, Integer>) conversionService.convert(map, sourceType, targetType);
		assertEquals(map, result);
		assertEquals(NoDefaultConstructorMap.class, result.getClass());
	}
