	@Test
	public void testSpr7766() throws Exception {
		ConverterRegistry registry = (conversionService);
		registry.addConverter(new ColorConverter());
		@SuppressWarnings("unchecked")
		List<Color> colors = (List<Color>) conversionService.convert(new String[] {"ffffff", "#000000"},
				TypeDescriptor.valueOf(String[].class),
				new TypeDescriptor(new MethodParameter(getClass().getMethod("handlerMethod", List.class), 0)));
		assertEquals(2, colors.size());
		assertEquals(Color.WHITE, colors.get(0));
		assertEquals(Color.BLACK, colors.get(1));
	}
