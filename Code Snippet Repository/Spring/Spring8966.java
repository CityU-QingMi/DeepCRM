	@Test
	public void withInteger() {
		Collection<Integer> col = new HashSet<>(1);
		col.add(5);

		assertEquals(Integer.valueOf(5), DataAccessUtils.uniqueResult(col));
		assertEquals(Integer.valueOf(5), DataAccessUtils.requiredUniqueResult(col));
		assertEquals(Integer.valueOf(5), DataAccessUtils.objectResult(col, Integer.class));
		assertEquals("5", DataAccessUtils.objectResult(col, String.class));
		assertEquals(5, DataAccessUtils.intResult(col));
		assertEquals(5, DataAccessUtils.longResult(col));
	}
