	@Test
	public void scalarMapNotGenericSourceField() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("1", "9");
		map.put("2", "37");
		TypeDescriptor sourceType = new TypeDescriptor(getClass().getField("notGenericMapSource"));
		TypeDescriptor targetType = new TypeDescriptor(getClass().getField("scalarMapTarget"));

		assertTrue(conversionService.canConvert(sourceType, targetType));
		try {
			conversionService.convert(map, sourceType, targetType);
		}
		catch (ConversionFailedException ex) {
			assertTrue(ex.getCause() instanceof ConverterNotFoundException);
		}

		conversionService.addConverterFactory(new StringToNumberConverterFactory());
		assertTrue(conversionService.canConvert(sourceType, targetType));
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> result = (Map<Integer, Integer>) conversionService.convert(map, sourceType, targetType);
		assertFalse(map.equals(result));
		assertEquals((Integer) 9, result.get(1));
		assertEquals((Integer) 37, result.get(2));
	}
