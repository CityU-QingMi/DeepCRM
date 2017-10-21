	@Test
	public void testPatternListFormatting() {
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("patternList", new String[] { "1,25.00", "2,35.00" });
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("1,25.00", binder.getBindingResult().getFieldValue("patternList[0]"));
		assertEquals("2,35.00", binder.getBindingResult().getFieldValue("patternList[1]"));

		propertyValues = new MutablePropertyValues();
		propertyValues.add("patternList[0]", "1,25.00");
		propertyValues.add("patternList[1]", "2,35.00");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("1,25.00", binder.getBindingResult().getFieldValue("patternList[0]"));
		assertEquals("2,35.00", binder.getBindingResult().getFieldValue("patternList[1]"));
	}
