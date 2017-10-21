	@Test
	public void testBindLocalDateTime() {
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("localDateTime", new LocalDateTime(2009, 10, 31, 12, 0));
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		String value = binder.getBindingResult().getFieldValue("localDateTime").toString();
		assertTrue(value.startsWith("10/31/09"));
		assertTrue(value.endsWith("12:00 PM"));
	}
