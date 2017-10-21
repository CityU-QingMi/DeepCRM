	@Test
	@Ignore
	public void testBindDateAnnotatedWithFallbackError() {
		// TODO This currently passes because of the Date(String) constructor fallback is used
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("dateAnnotated", "Oct 031, 2009");
		binder.bind(propertyValues);
		assertEquals(1, binder.getBindingResult().getFieldErrorCount("dateAnnotated"));
		assertEquals("Oct 031, 2009", binder.getBindingResult().getFieldValue("dateAnnotated"));
	}
