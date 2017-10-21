	@Test
	public void testStringToEnumMap() throws Exception {
		conversionService.addConverterFactory(new StringToEnumConverterFactory());
		Map<String, Integer> source = new HashMap<>();
		source.put("A", 1);
		source.put("C", 2);
		EnumMap<MyEnum, Integer> result = new EnumMap<>(MyEnum.class);
		result.put(MyEnum.A, 1);
		result.put(MyEnum.C, 2);

		assertEquals(result, conversionService.convert(source,
				TypeDescriptor.forObject(source), new TypeDescriptor(getClass().getField("enumMap"))));
	}
