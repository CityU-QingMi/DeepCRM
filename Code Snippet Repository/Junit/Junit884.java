	@Test
	void findMethodByParameterTypesPreconditions() throws Exception {
		// @formatter:off
		assertThrows(PreconditionViolationException.class, () -> findMethod(null, null));
		assertThrows(PreconditionViolationException.class, () -> findMethod(null, "method"));

		RuntimeException exception = assertThrows(PreconditionViolationException.class, () -> findMethod(String.class, null));
		assertThat(exception).hasMessage("Method name must not be null or blank");

		exception = assertThrows(PreconditionViolationException.class, () -> findMethod(String.class, "   "));
		assertThat(exception).hasMessage("Method name must not be null or blank");

		exception = assertThrows(PreconditionViolationException.class, () -> findMethod(Files.class, "copy", (Class<?>[]) null));
		assertThat(exception).hasMessage("Parameter types array must not be null");

		exception = assertThrows(PreconditionViolationException.class, () -> findMethod(Files.class, "copy", (Class<?>) null));
		assertThat(exception).hasMessage("Individual parameter types must not be null");

		exception = assertThrows(PreconditionViolationException.class, () -> findMethod(Files.class, "copy", new Class<?>[] { Path.class, null }));
		assertThat(exception).hasMessage("Individual parameter types must not be null");
		// @formatter:on
	}
