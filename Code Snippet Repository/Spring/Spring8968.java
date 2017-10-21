	@Test
	@SuppressWarnings("")
	public void withEquivalentIntegerInstanceTwice() {
		Collection<Integer> col = new ArrayList<>(2);
		col.add(new Integer(5));
		col.add(new Integer(5));

		try {
			DataAccessUtils.uniqueResult(col);
			fail("Should have thrown IncorrectResultSizeDataAccessException");
		}
		catch (IncorrectResultSizeDataAccessException ex) {
			// expected
			assertEquals(1, ex.getExpectedSize());
			assertEquals(2, ex.getActualSize());
		}
	}
