	@Test
	public void scalarList() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("9");
		list.add("37");
		TypeDescriptor sourceType = TypeDescriptor.forObject(list);
		TypeDescriptor targetType = new TypeDescriptor(getClass().getField("scalarListTarget"));
		assertTrue(conversionService.canConvert(sourceType, targetType));
		try {
			conversionService.convert(list, sourceType, targetType);
		}
		catch (ConversionFailedException ex) {
			assertTrue(ex.getCause() instanceof ConverterNotFoundException);
		}
		conversionService.addConverterFactory(new StringToNumberConverterFactory());
		assertTrue(conversionService.canConvert(sourceType, targetType));
		@SuppressWarnings("unchecked")
		List<Integer> result = (List<Integer>) conversionService.convert(list, sourceType, targetType);
		assertFalse(list.equals(result));
		assertEquals(9, result.get(0).intValue());
		assertEquals(37, result.get(1).intValue());
	}
