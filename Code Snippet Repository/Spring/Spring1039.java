	@Test
	public void testStandardEnumMapWithAutoGrowing() {
		GenericBean<?> gb = new GenericBean<>();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setAutoGrowNestedPaths(true);
		assertNull(gb.getStandardEnumMap());
		bw.setPropertyValue("standardEnumMap[VALUE_1]", 1);
		assertEquals(1, gb.getStandardEnumMap().size());
		assertEquals(new Integer(1), gb.getStandardEnumMap().get(CustomEnum.VALUE_1));
	}
