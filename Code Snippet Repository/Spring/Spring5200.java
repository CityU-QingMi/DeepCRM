	@Test
	public void testStringToBooleanFalse() {
		assertEquals(false, conversionService.convert("false", Boolean.class));
		assertEquals(false, conversionService.convert("off", Boolean.class));
		assertEquals(false, conversionService.convert("no", Boolean.class));
		assertEquals(false, conversionService.convert("0", Boolean.class));
		assertEquals(false, conversionService.convert("FALSE", Boolean.class));
		assertEquals(false, conversionService.convert("OFF", Boolean.class));
		assertEquals(false, conversionService.convert("NO", Boolean.class));
	}
