	@Test
	public void isAssignableFromForClassAndClass() throws Exception {
		ResolvableType objectType = ResolvableType.forClass(Object.class);
		ResolvableType charSequenceType = ResolvableType.forClass(CharSequence.class);
		ResolvableType stringType = ResolvableType.forClass(String.class);

		assertAssignable(objectType, objectType, charSequenceType, stringType).equalTo(true, true, true);
		assertAssignable(charSequenceType, objectType, charSequenceType, stringType).equalTo(false, true, true);
		assertAssignable(stringType, objectType, charSequenceType, stringType).equalTo(false, false, true);

		assertTrue(objectType.isAssignableFrom(String.class));
		assertTrue(objectType.isAssignableFrom(StringBuilder.class));
		assertTrue(charSequenceType.isAssignableFrom(String.class));
		assertTrue(charSequenceType.isAssignableFrom(StringBuilder.class));
		assertTrue(stringType.isAssignableFrom(String.class));
		assertFalse(stringType.isAssignableFrom(StringBuilder.class));

		assertTrue(objectType.isInstance("a String"));
		assertTrue(objectType.isInstance(new StringBuilder("a StringBuilder")));
		assertTrue(charSequenceType.isInstance("a String"));
		assertTrue(charSequenceType.isInstance(new StringBuilder("a StringBuilder")));
		assertTrue(stringType.isInstance("a String"));
		assertFalse(stringType.isInstance(new StringBuilder("a StringBuilder")));
	}
