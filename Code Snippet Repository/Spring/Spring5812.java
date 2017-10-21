	@Test
	public void testPrimitives() throws EvaluationException {
		TypeComparator comparator = new StandardTypeComparator();
		// primitive int
		assertTrue(comparator.compare(1, 2) < 0);
		assertTrue(comparator.compare(1, 1) == 0);
		assertTrue(comparator.compare(2, 1) > 0);

		assertTrue(comparator.compare(1.0d, 2) < 0);
		assertTrue(comparator.compare(1.0d, 1) == 0);
		assertTrue(comparator.compare(2.0d, 1) > 0);

		assertTrue(comparator.compare(1.0f, 2) < 0);
		assertTrue(comparator.compare(1.0f, 1) == 0);
		assertTrue(comparator.compare(2.0f, 1) > 0);

		assertTrue(comparator.compare(1L, 2) < 0);
		assertTrue(comparator.compare(1L, 1) == 0);
		assertTrue(comparator.compare(2L, 1) > 0);

		assertTrue(comparator.compare(1, 2L) < 0);
		assertTrue(comparator.compare(1, 1L) == 0);
		assertTrue(comparator.compare(2, 1L) > 0);

		assertTrue(comparator.compare(1L, 2L) < 0);
		assertTrue(comparator.compare(1L, 1L) == 0);
		assertTrue(comparator.compare(2L, 1L) > 0);
	}
