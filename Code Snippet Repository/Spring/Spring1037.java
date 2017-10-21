	@Test
	public void testStandardEnumSetWithMultipleValues() {
		GenericBean<?> gb = new GenericBean<>();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setConversionService(new DefaultConversionService());
		assertNull(gb.getStandardEnumSet());
		bw.setPropertyValue("standardEnumSet", new String[] {"VALUE_1", "VALUE_2"});
		assertEquals(2, gb.getStandardEnumSet().size());
		assertTrue(gb.getStandardEnumSet().contains(CustomEnum.VALUE_1));
		assertTrue(gb.getStandardEnumSet().contains(CustomEnum.VALUE_2));
	}
