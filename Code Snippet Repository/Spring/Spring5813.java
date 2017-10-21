	@Test
	public void testNonPrimitiveNumbers() throws EvaluationException {
		TypeComparator comparator = new StandardTypeComparator();

		BigDecimal bdOne = new BigDecimal("1");
		BigDecimal bdTwo = new BigDecimal("2");

		assertTrue(comparator.compare(bdOne, bdTwo) < 0);
		assertTrue(comparator.compare(bdOne, new BigDecimal("1")) == 0);
		assertTrue(comparator.compare(bdTwo, bdOne) > 0);

		assertTrue(comparator.compare(1, bdTwo) < 0);
		assertTrue(comparator.compare(1, bdOne) == 0);
		assertTrue(comparator.compare(2, bdOne) > 0);

		assertTrue(comparator.compare(1.0d, bdTwo) < 0);
		assertTrue(comparator.compare(1.0d, bdOne) == 0);
		assertTrue(comparator.compare(2.0d, bdOne) > 0);

		assertTrue(comparator.compare(1.0f, bdTwo) < 0);
		assertTrue(comparator.compare(1.0f, bdOne) == 0);
		assertTrue(comparator.compare(2.0f, bdOne) > 0);

		assertTrue(comparator.compare(1L, bdTwo) < 0);
		assertTrue(comparator.compare(1L, bdOne) == 0);
		assertTrue(comparator.compare(2L, bdOne) > 0);

	}
