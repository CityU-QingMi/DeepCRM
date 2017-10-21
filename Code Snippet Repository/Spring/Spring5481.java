	@Test
	public void isCompatibleWithThrowsClause() {
		Class<?>[] empty = new Class[0];
		Class<?>[] exception = new Class[] {Exception.class};
		Class<?>[] sqlAndIO = new Class[] {SQLException.class, IOException.class};
		Class<?>[] throwable = new Class[] {Throwable.class};

		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new RuntimeException()));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new RuntimeException(), empty));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new RuntimeException(), exception));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new RuntimeException(), sqlAndIO));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new RuntimeException(), throwable));

		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new Exception()));
		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new Exception(), empty));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new Exception(), exception));
		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new Exception(), sqlAndIO));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new Exception(), throwable));

		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new SQLException()));
		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new SQLException(), empty));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new SQLException(), exception));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new SQLException(), sqlAndIO));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new SQLException(), throwable));

		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new Throwable()));
		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new Throwable(), empty));
		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new Throwable(), exception));
		assertFalse(ObjectUtils.isCompatibleWithThrowsClause(new Throwable(), sqlAndIO));
		assertTrue(ObjectUtils.isCompatibleWithThrowsClause(new Throwable(), throwable));
	}
