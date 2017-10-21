	@Test
	public void conditionalConverterCachingForDifferentAnnotationAttributes() throws Exception {
		conversionService.addConverter(new ColorConverter());
		conversionService.addConverter(new MyConditionalColorConverter());

		assertEquals(Color.BLACK, conversionService.convert("000000xxxx",
				new TypeDescriptor(getClass().getField("activeColor"))));
		assertEquals(Color.BLACK, conversionService.convert(" #000000 ",
				new TypeDescriptor(getClass().getField("inactiveColor"))));
		assertEquals(Color.BLACK, conversionService.convert("000000yyyy",
				new TypeDescriptor(getClass().getField("activeColor"))));
		assertEquals(Color.BLACK, conversionService.convert("  #000000  ",
				new TypeDescriptor(getClass().getField("inactiveColor"))));
	}
