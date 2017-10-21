	@Test
	public void testPatternList2FormattingListElement() {
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("patternList2[0]", "1,25.00");
		propertyValues.add("patternList2[1]", "2,35.00");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("1,25.00", binder.getBindingResult().getFieldValue("patternList2[0]"));
		assertEquals("2,35.00", binder.getBindingResult().getFieldValue("patternList2[1]"));
	}
