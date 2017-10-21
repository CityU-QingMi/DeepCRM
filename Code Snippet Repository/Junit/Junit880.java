	@Test
	void containsNoNullElementsThrowsForArrayContainingNullElements() {
		String message = "array contains null elements";
		Object[] array = { new Object(), null, new Object() };

		PreconditionViolationException exception = assertThrows(PreconditionViolationException.class,
			() -> containsNoNullElements(array, message));

		assertEquals(message, exception.getMessage());
	}
