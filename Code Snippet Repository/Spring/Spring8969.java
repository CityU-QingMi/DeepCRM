	@Test
	public void withLong() {
		Collection<Long> col = new HashSet<>(1);
		col.add(5L);

		assertEquals(Long.valueOf(5L), DataAccessUtils.uniqueResult(col));
		assertEquals(Long.valueOf(5L), DataAccessUtils.requiredUniqueResult(col));
		assertEquals(Long.valueOf(5L), DataAccessUtils.objectResult(col, Long.class));
		assertEquals("5", DataAccessUtils.objectResult(col, String.class));
		assertEquals(5, DataAccessUtils.intResult(col));
		assertEquals(5, DataAccessUtils.longResult(col));
	}
