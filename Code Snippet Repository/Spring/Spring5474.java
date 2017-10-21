	@Test
	public void isCheckedException() {
		assertTrue(ObjectUtils.isCheckedException(new Exception()));
		assertTrue(ObjectUtils.isCheckedException(new SQLException()));

		assertFalse(ObjectUtils.isCheckedException(new RuntimeException()));
		assertFalse(ObjectUtils.isCheckedException(new IllegalArgumentException("")));

		// Any Throwable other than RuntimeException and Error
		// has to be considered checked according to the JLS.
		assertTrue(ObjectUtils.isCheckedException(new Throwable()));
	}
