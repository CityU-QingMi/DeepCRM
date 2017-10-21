	@Test
	public void testCanCompare() throws EvaluationException {
		TypeComparator comparator = new StandardTypeComparator();
		assertTrue(comparator.canCompare(null,1));
		assertTrue(comparator.canCompare(1,null));

		assertTrue(comparator.canCompare(2,1));
		assertTrue(comparator.canCompare("abc","def"));
		assertTrue(comparator.canCompare("abc",3));
		assertFalse(comparator.canCompare(String.class,3));
	}
