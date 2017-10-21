	@Test
	public void shouldNotSupportNullConvertibleTypesFromNonConditionalGenericConverter() {
		GenericConverter converter = new NonConditionalGenericConverter();
		try {
			conversionService.addConverter(converter);
			fail("Did not throw IllegalStateException");
		}
		catch (IllegalStateException ex) {
			assertEquals("Only conditional converters may return null convertible types", ex.getMessage());
		}
	}
