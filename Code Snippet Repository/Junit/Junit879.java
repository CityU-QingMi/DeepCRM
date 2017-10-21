	@Test
	void notNullThrowsForNullObjectAndMessageSupplier() {
		String message = "argument is null";
		Object object = null;

		PreconditionViolationException exception = assertThrows(PreconditionViolationException.class,
			() -> notNull(object, () -> message));

		assertEquals(message, exception.getMessage());
	}
