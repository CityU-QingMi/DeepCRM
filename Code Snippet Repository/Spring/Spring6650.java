	private void doTest(Method rsetMethod, Method rowsetMethod, Object arg, Object ret) throws Exception {
		if (arg instanceof String) {
			given(rset.findColumn((String) arg)).willReturn(1);
			given(rsetMethod.invoke(rset, 1)).willReturn(ret).willThrow(new SQLException("test"));
		}
		else {
			given(rsetMethod.invoke(rset, arg)).willReturn(ret).willThrow(new SQLException("test"));
		}
		rowsetMethod.invoke(rowset, arg);
		try {
			rowsetMethod.invoke(rowset, arg);
			fail("InvalidResultSetAccessException should have been thrown");
		}
		catch (InvocationTargetException ex) {
			assertEquals(InvalidResultSetAccessException.class, ex.getTargetException().getClass());
		}
	}
