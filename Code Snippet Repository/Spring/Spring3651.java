	@Test
	public void testDefaultFormattersOn() throws Exception {
		FormattingConversionServiceFactoryBean factory = new FormattingConversionServiceFactoryBean();
		factory.afterPropertiesSet();
		FormattingConversionService fcs = factory.getObject();
		TypeDescriptor descriptor = new TypeDescriptor(TestBean.class.getDeclaredField("pattern"));

		LocaleContextHolder.setLocale(Locale.GERMAN);
		try {
			Object value = fcs.convert("15,00", TypeDescriptor.valueOf(String.class), descriptor);
			assertEquals(15.0, value);
			value = fcs.convert(15.0, descriptor, TypeDescriptor.valueOf(String.class));
			assertEquals("15", value);
		}
		finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}
