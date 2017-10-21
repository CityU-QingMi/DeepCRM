	@Test
	public void setRequiredProperties_andValidateRequiredProperties() {
		// no properties have been marked as required -> validation should pass
		propertyResolver.validateRequiredProperties();

		// mark which properties are required
		propertyResolver.setRequiredProperties("foo", "bar");

		// neither foo nor bar properties are present -> validating should throw
		try {
			propertyResolver.validateRequiredProperties();
			fail("expected validation exception");
		}
		catch (MissingRequiredPropertiesException ex) {
			assertThat(ex.getMessage(), equalTo(
					"The following properties were declared as required " +
					"but could not be resolved: [foo, bar]"));
		}

		// add foo property -> validation should fail only on missing 'bar' property
		testProperties.put("foo", "fooValue");
		try {
			propertyResolver.validateRequiredProperties();
			fail("expected validation exception");
		}
		catch (MissingRequiredPropertiesException ex) {
			assertThat(ex.getMessage(), equalTo(
					"The following properties were declared as required " +
					"but could not be resolved: [bar]"));
		}

		// add bar property -> validation should pass, even with an empty string value
		testProperties.put("bar", "");
		propertyResolver.validateRequiredProperties();
	}
