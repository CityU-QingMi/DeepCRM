	@Test
	public void setFieldWithNullValuesForNonPrimitives() throws Exception {
		// Fields must be non-null to start with
		setField(person, "name", "Tom");
		setField(person, "eyeColor", "blue", String.class);
		setField(person, "favoriteNumber", PI, Number.class);
		assertNotNull(person.getName());
		assertNotNull(person.getEyeColor());
		assertNotNull(person.getFavoriteNumber());

		// Set to null
		setField(person, "name", null, String.class);
		setField(person, "eyeColor", null, String.class);
		setField(person, "favoriteNumber", null, Number.class);

		assertNull("name (protected field)", person.getName());
		assertNull("eye color (package private field)", person.getEyeColor());
		assertNull("'favorite number' (package field)", person.getFavoriteNumber());
	}
