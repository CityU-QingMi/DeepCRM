	@Test
	void findMethodsPreconditions() {
		// @formatter:off
		assertThrows(PreconditionViolationException.class, () -> findMethods(null, null));
		assertThrows(PreconditionViolationException.class, () -> findMethods(null, clazz -> true));
		assertThrows(PreconditionViolationException.class, () -> findMethods(String.class, null));

		assertThrows(PreconditionViolationException.class, () -> findMethods(null, null, null));
		assertThrows(PreconditionViolationException.class, () -> findMethods(null, clazz -> true, BOTTOM_UP));
		assertThrows(PreconditionViolationException.class, () -> findMethods(String.class, null, BOTTOM_UP));
		assertThrows(PreconditionViolationException.class, () -> findMethods(String.class, clazz -> true, null));
		// @formatter:on
	}
