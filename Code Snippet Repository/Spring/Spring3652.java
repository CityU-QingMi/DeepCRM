	@Test
	public void testDefaultFormattersOff() throws Exception {
		FormattingConversionServiceFactoryBean factory = new FormattingConversionServiceFactoryBean();
		factory.setRegisterDefaultFormatters(false);
		factory.afterPropertiesSet();
		FormattingConversionService fcs = factory.getObject();
		TypeDescriptor descriptor = new TypeDescriptor(TestBean.class.getDeclaredField("pattern"));

		try {
			fcs.convert("15,00", TypeDescriptor.valueOf(String.class), descriptor);
			fail("This format should not be parseable");
		}
		catch (ConversionFailedException ex) {
			assertTrue(ex.getCause() instanceof NumberFormatException);
		}
	}
