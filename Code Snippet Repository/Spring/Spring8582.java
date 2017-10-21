	@Test
	public void invokeSetterMethodWithNullValuesForNonPrimitives() throws Exception {
		invokeSetterMethod(person, "name", null, String.class);
		invokeSetterMethod(person, "eyeColor", null, String.class);
		invokeSetterMethod(person, "favoriteNumber", null, Number.class);

		assertNull("name (private method)", person.getName());
		assertNull("eye color (package private method)", person.getEyeColor());
		assertNull("'favorite number' (protected method for a Number)", person.getFavoriteNumber());
	}
