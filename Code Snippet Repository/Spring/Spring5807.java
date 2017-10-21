	@Test
	public void testConvertAndHandleNull() { // SPR-9445
		// without null conversion
		evaluateAndCheckError("null or true", SpelMessage.TYPE_CONVERSION_ERROR, 0, "null", "boolean");
		evaluateAndCheckError("null and true", SpelMessage.TYPE_CONVERSION_ERROR, 0, "null", "boolean");
		evaluateAndCheckError("!null", SpelMessage.TYPE_CONVERSION_ERROR, 1, "null", "boolean");
		evaluateAndCheckError("null ? 'foo' : 'bar'", SpelMessage.TYPE_CONVERSION_ERROR, 0, "null", "boolean");

		// with null conversion (null -> false)
		GenericConversionService conversionService = new GenericConversionService() {
			@Override
			protected Object convertNullSource(TypeDescriptor sourceType, TypeDescriptor targetType) {
				return targetType.getType() == Boolean.class ? false : null;
			}
		};
		eContext.setTypeConverter(new StandardTypeConverter(conversionService));

		evaluate("null or true", Boolean.TRUE, Boolean.class, false);
		evaluate("null and true", Boolean.FALSE, Boolean.class, false);
		evaluate("!null", Boolean.TRUE, Boolean.class, false);
		evaluate("null ? 'foo' : 'bar'", "bar", String.class, false);
	}
