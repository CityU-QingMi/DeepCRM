	@Test
	public void testStringToBooleanTrue() {
		assertEquals(true, conversionService.convert("true", Boolean.class));
		assertEquals(true, conversionService.convert("on", Boolean.class));
		assertEquals(true, conversionService.convert("yes", Boolean.class));
		assertEquals(true, conversionService.convert("1", Boolean.class));
		assertEquals(true, conversionService.convert("TRUE", Boolean.class));
		assertEquals(true, conversionService.convert("ON", Boolean.class));
		assertEquals(true, conversionService.convert("YES", Boolean.class));
	}
