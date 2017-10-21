	@Test
	public void testInvalidFormatter() throws Exception {
		FormattingConversionServiceFactoryBean factory = new FormattingConversionServiceFactoryBean();
		Set<Object> formatters = new HashSet<>();
		formatters.add(new Object());
		factory.setFormatters(formatters);
		try {
			factory.afterPropertiesSet();
			fail("Expected formatter to be rejected");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
