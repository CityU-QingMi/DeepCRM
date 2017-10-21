	@Test
	public void testNoIterators() {
		CompositeIterator<String> it = new CompositeIterator<>();
		assertFalse(it.hasNext());
		try {
			it.next();
			fail();
		}
		catch (NoSuchElementException ex) {
			// expected
		}
	}
