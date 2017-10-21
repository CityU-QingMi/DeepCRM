	@Test
	public void withSameIntegerInstanceTwice() {
		Integer i = 5;
		Collection<Integer> col = new ArrayList<>(1);
		col.add(i);
		col.add(i);

		assertEquals(Integer.valueOf(5), DataAccessUtils.uniqueResult(col));
		assertEquals(Integer.valueOf(5), DataAccessUtils.requiredUniqueResult(col));
		assertEquals(Integer.valueOf(5), DataAccessUtils.objectResult(col, Integer.class));
		assertEquals("5", DataAccessUtils.objectResult(col, String.class));
		assertEquals(5, DataAccessUtils.intResult(col));
		assertEquals(5, DataAccessUtils.longResult(col));
	}
