	@Test
	public void testBindLocalDateTimeAnnotated() {
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("localDateTimeAnnotated", LocalDateTime.of(2009, 10, 31, 12, 0));
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		String value = binder.getBindingResult().getFieldValue("localDateTimeAnnotated").toString();
		assertTrue(value.startsWith("Oct 31, 2009"));
		assertTrue(value.endsWith("12:00:00 PM"));
	}
