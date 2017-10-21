	protected <T> void assertEqualsAndHashCode(T equal1, T equal2, T different) {
		assertNotNull(equal1);
		assertNotNull(equal2);
		assertNotNull(different);

		assertNotSame(equal1, equal2);
		assertFalse(equal1.equals(null));
		assertFalse(equal1.equals(different));
		assertFalse(different.equals(equal1));
		assertNotEquals(equal1.hashCode(), different.hashCode());

		assertTrue(equal1.equals(equal1));
		assertTrue(equal1.equals(equal2));
		assertTrue(equal2.equals(equal1));
		assertEquals(equal1.hashCode(), equal2.hashCode());
	}
