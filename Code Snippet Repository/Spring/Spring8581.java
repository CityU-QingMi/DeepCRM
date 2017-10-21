	@Test
	public void invokeSetterMethodAndInvokeGetterMethodWithJavaBeanPropertyNames() throws Exception {
		invokeSetterMethod(person, "id", new Long(99), long.class);
		invokeSetterMethod(person, "name", "Tom");
		invokeSetterMethod(person, "age", new Integer(42));
		invokeSetterMethod(person, "eyeColor", "blue", String.class);
		invokeSetterMethod(person, "likesPets", Boolean.TRUE);
		invokeSetterMethod(person, "favoriteNumber", PI, Number.class);

		assertEquals("ID (protected method in a superclass)", 99, person.getId());
		assertEquals("name (private method)", "Tom", person.getName());
		assertEquals("age (protected method)", 42, person.getAge());
		assertEquals("eye color (package private method)", "blue", person.getEyeColor());
		assertEquals("'likes pets' flag (protected method for a boolean)", true, person.likesPets());
		assertEquals("'favorite number' (protected method for a Number)", PI, person.getFavoriteNumber());

		assertEquals(new Long(99), invokeGetterMethod(person, "id"));
		assertEquals("Tom", invokeGetterMethod(person, "name"));
		assertEquals(new Integer(42), invokeGetterMethod(person, "age"));
		assertEquals("blue", invokeGetterMethod(person, "eyeColor"));
		assertEquals(Boolean.TRUE, invokeGetterMethod(person, "likesPets"));
		assertEquals(PI, invokeGetterMethod(person, "favoriteNumber"));
	}
