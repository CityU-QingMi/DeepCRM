	@Test
	public void resolveConfigAttributesWithConflictingLocations() {
		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(containsString(ConflictingLocations.class.getName()));
		exception.expectMessage(either(
				containsString("attribute 'value' and its alias 'locations'")).or(
				containsString("attribute 'locations' and its alias 'value'")));
		exception.expectMessage(either(
				containsString("values of [{x}] and [{y}]")).or(
				containsString("values of [{y}] and [{x}]")));
		exception.expectMessage(containsString("but only one is permitted"));
		resolveContextConfigurationAttributes(ConflictingLocations.class);
	}
