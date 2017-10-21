	@Test
	public void getProperty_withNonConvertibleTargetType() {
		testProperties.put("foo", "bar");

		class TestType { }

		try {
			propertyResolver.getProperty("foo", TestType.class);
			fail("Expected ConverterNotFoundException due to non-convertible types");
		}
		catch (ConverterNotFoundException ex) {
			// expected
		}
	}
