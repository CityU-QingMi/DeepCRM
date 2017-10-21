	@Test
	void newInstance() {
		// @formatter:off
		assertThat(ReflectionUtils.newInstance(C.class, "one", "two")).isNotNull();
		assertThat(ReflectionUtils.newInstance(C.class)).isNotNull();
		assertThat(ReflectionUtils.newInstance(C.class, new Object[0])).isNotNull();

		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.newInstance(C.class, "one", null));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.newInstance(C.class, null, "two"));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.newInstance(C.class, null, null));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.newInstance(C.class, ((Object[]) null)));

		RuntimeException exception = assertThrows(RuntimeException.class, () -> ReflectionUtils.newInstance(Exploder.class));
		assertThat(exception).hasMessage("boom");
		// @formatter:on
	}
