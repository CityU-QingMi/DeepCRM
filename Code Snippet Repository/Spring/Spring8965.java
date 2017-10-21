	@Test
	public void withEmptyCollection() {
		Collection<String> col = new HashSet<>();

		assertNull(DataAccessUtils.uniqueResult(col));

		try {
			DataAccessUtils.requiredUniqueResult(col);
			fail("Should have thrown IncorrectResultSizeDataAccessException");
		}
		catch (IncorrectResultSizeDataAccessException ex) {
			// expected
			assertEquals(1, ex.getExpectedSize());
			assertEquals(0, ex.getActualSize());
		}

		try {
			DataAccessUtils.objectResult(col, String.class);
			fail("Should have thrown IncorrectResultSizeDataAccessException");
		}
		catch (IncorrectResultSizeDataAccessException ex) {
			// expected
			assertEquals(1, ex.getExpectedSize());
			assertEquals(0, ex.getActualSize());
		}

		try {
			DataAccessUtils.intResult(col);
			fail("Should have thrown IncorrectResultSizeDataAccessException");
		}
		catch (IncorrectResultSizeDataAccessException ex) {
			// expected
			assertEquals(1, ex.getExpectedSize());
			assertEquals(0, ex.getActualSize());
		}

		try {
			DataAccessUtils.longResult(col);
			fail("Should have thrown IncorrectResultSizeDataAccessException");
		}
		catch (IncorrectResultSizeDataAccessException ex) {
			// expected
			assertEquals(1, ex.getExpectedSize());
			assertEquals(0, ex.getActualSize());
		}
	}
