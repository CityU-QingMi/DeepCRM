	@Test
	public void setUnknownOptionalProperty() {
		Simple target = new Simple("John", 2);
		AbstractPropertyAccessor accessor = createAccessor(target);

		try {
			PropertyValue value = new PropertyValue("foo", "value");
			value.setOptional(true);
			accessor.setPropertyValue(value);
		}
		catch (NotWritablePropertyException e) {
			fail("Should not have failed to set an unknown optional property.");
		}
	}
