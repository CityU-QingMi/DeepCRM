	@Test
	public void containsConstant() {
		assertThat(ObjectUtils.containsConstant(Tropes.values(), "FOO"), is(true));
		assertThat(ObjectUtils.containsConstant(Tropes.values(), "foo"), is(true));
		assertThat(ObjectUtils.containsConstant(Tropes.values(), "BaR"), is(true));
		assertThat(ObjectUtils.containsConstant(Tropes.values(), "bar"), is(true));
		assertThat(ObjectUtils.containsConstant(Tropes.values(), "BAZ"), is(true));
		assertThat(ObjectUtils.containsConstant(Tropes.values(), "baz"), is(true));

		assertThat(ObjectUtils.containsConstant(Tropes.values(), "BOGUS"), is(false));

		assertThat(ObjectUtils.containsConstant(Tropes.values(), "FOO", true), is(true));
		assertThat(ObjectUtils.containsConstant(Tropes.values(), "foo", true), is(false));
	}
