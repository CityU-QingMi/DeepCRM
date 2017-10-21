	@Test
	public void testFormatterRegistrar() throws Exception {
		FormattingConversionServiceFactoryBean factory = new FormattingConversionServiceFactoryBean();
		Set<FormatterRegistrar> registrars = new HashSet<>();
		registrars.add(new TestFormatterRegistrar());
		factory.setFormatterRegistrars(registrars);
		factory.afterPropertiesSet();
		FormattingConversionService fcs = factory.getObject();

		TestBean testBean = fcs.convert("5", TestBean.class);
		assertEquals(5, testBean.getSpecialInt());
		assertEquals("5", fcs.convert(testBean, String.class));
	}
