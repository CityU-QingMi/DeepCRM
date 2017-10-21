	@Test
	public void withString() {
		Collection<String> col = new HashSet<>(1);
		col.add("test1");

		assertEquals("test1", DataAccessUtils.uniqueResult(col));
		assertEquals("test1", DataAccessUtils.requiredUniqueResult(col));
		assertEquals("test1", DataAccessUtils.objectResult(col, String.class));

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
