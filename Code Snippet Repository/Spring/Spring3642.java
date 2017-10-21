	@Test
	public void testPatternArrayFormatting() {
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("patternArray", new String[] { "1,25.00", "2,35.00" });
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("1,25.00", binder.getBindingResult().getFieldValue("patternArray[0]"));
		assertEquals("2,35.00", binder.getBindingResult().getFieldValue("patternArray[1]"));

		propertyValues = new MutablePropertyValues();
		propertyValues.add("patternArray[0]", "1,25.00");
		propertyValues.add("patternArray[1]", "2,35.00");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("1,25.00", binder.getBindingResult().getFieldValue("patternArray[0]"));
		assertEquals("2,35.00", binder.getBindingResult().getFieldValue("patternArray[1]"));
	}
