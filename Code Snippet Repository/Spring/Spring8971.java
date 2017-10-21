	@Test
	public void withDate() {
		Date date = new Date();
		Collection<Date> col = new HashSet<>(1);
		col.add(date);

		assertEquals(date, DataAccessUtils.uniqueResult(col));
		assertEquals(date, DataAccessUtils.requiredUniqueResult(col));
		assertEquals(date, DataAccessUtils.objectResult(col, Date.class));
		assertEquals(date.toString(), DataAccessUtils.objectResult(col, String.class));

		try {
			DataAccessUtils.intResult(col);
			fail("Should have thrown TypeMismatchDataAccessException");
		}
		catch (TypeMismatchDataAccessException ex) {
			// expected
		}

		try {
			DataAccessUtils.longResult(col);
			fail("Should have thrown TypeMismatchDataAccessException");
		}
		catch (TypeMismatchDataAccessException ex) {
			// expected
		}
	}
