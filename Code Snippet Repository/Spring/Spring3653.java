	@Test
	public void testCustomFormatter() throws Exception {
		FormattingConversionServiceFactoryBean factory = new FormattingConversionServiceFactoryBean();
		Set<Object> formatters = new HashSet<>();
		formatters.add(new TestBeanFormatter());
		formatters.add(new SpecialIntAnnotationFormatterFactory());
		factory.setFormatters(formatters);
		factory.afterPropertiesSet();
		FormattingConversionService fcs = factory.getObject();

		TestBean testBean = fcs.convert("5", TestBean.class);
		assertEquals(5, testBean.getSpecialInt());
		assertEquals("5", fcs.convert(testBean, String.class));

		TypeDescriptor descriptor = new TypeDescriptor(TestBean.class.getDeclaredField("specialInt"));
		Object value = fcs.convert(":5", TypeDescriptor.valueOf(String.class), descriptor);
		assertEquals(5, value);
		value = fcs.convert(5, descriptor, TypeDescriptor.valueOf(String.class));
		assertEquals(":5", value);
	}
