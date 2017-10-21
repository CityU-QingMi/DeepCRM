	@Test
	public void invokeSetterMethodAndInvokeGetterMethodWithExplicitMethodNames() throws Exception {
		invokeSetterMethod(person, "setId", new Long(1), long.class);
		invokeSetterMethod(person, "setName", "Jerry", String.class);
		invokeSetterMethod(person, "setAge", new Integer(33), int.class);
		invokeSetterMethod(person, "setEyeColor", "green", String.class);
		invokeSetterMethod(person, "setLikesPets", Boolean.FALSE, boolean.class);
		invokeSetterMethod(person, "setFavoriteNumber", new Integer(42), Number.class);

		assertEquals("ID (protected method in a superclass)", 1, person.getId());
		assertEquals("name (private method)", "Jerry", person.getName());
		assertEquals("age (protected method)", 33, person.getAge());
		assertEquals("eye color (package private method)", "green", person.getEyeColor());
		assertEquals("'likes pets' flag (protected method for a boolean)", false, person.likesPets());
		assertEquals("'favorite number' (protected method for a Number)", new Integer(42), person.getFavoriteNumber());

		assertEquals(new Long(1), invokeGetterMethod(person, "getId"));
		assertEquals("Jerry", invokeGetterMethod(person, "getName"));
		assertEquals(new Integer(33), invokeGetterMethod(person, "getAge"));
		assertEquals("green", invokeGetterMethod(person, "getEyeColor"));
		assertEquals(Boolean.FALSE, invokeGetterMethod(person, "likesPets"));
		assertEquals(new Integer(42), invokeGetterMethod(person, "getFavoriteNumber"));
	}
