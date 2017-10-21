	@Test
	public void testStandardEnumMapWithMultipleValues() {
		GenericBean<?> gb = new GenericBean<>();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setConversionService(new DefaultConversionService());
		assertNull(gb.getStandardEnumMap());
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("VALUE_1", 1);
		map.put("VALUE_2", 2);
		bw.setPropertyValue("standardEnumMap", map);
		assertEquals(2, gb.getStandardEnumMap().size());
		assertEquals(new Integer(1), gb.getStandardEnumMap().get(CustomEnum.VALUE_1));
		assertEquals(new Integer(2), gb.getStandardEnumMap().get(CustomEnum.VALUE_2));
	}
