	private void checkComposite(Component composite) throws Exception {
		// check `eyeColor`
		final Property eyeColorProperty = composite.getProperty( "eyeColor" );
		final SimpleValue eyeColorValueMapping = (SimpleValue) eyeColorProperty.getValue();
		assertThat( simpleValueAttributeConverterDescriptorField.get( eyeColorValueMapping ), CoreMatchers.notNullValue() );

		// check `hairColor`
		final Property hairColorProperty = composite.getProperty( "hairColor" );
		final SimpleValue hairColorValueMapping = (SimpleValue) hairColorProperty.getValue();
		assertThat( simpleValueAttributeConverterDescriptorField.get( hairColorValueMapping ), CoreMatchers.notNullValue() );

	}
