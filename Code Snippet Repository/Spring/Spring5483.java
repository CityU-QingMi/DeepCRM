	@Test
	public void isEmptyCharSequence() {
		assertTrue(isEmpty(new StringBuilder()));
		assertTrue(isEmpty(""));

		assertFalse(isEmpty(new StringBuilder("foo")));
		assertFalse(isEmpty("   "));
		assertFalse(isEmpty("\t"));
		assertFalse(isEmpty("foo"));
	}
