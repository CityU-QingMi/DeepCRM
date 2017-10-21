	@Test
	public void withDefaultNonOptionArgsNameAndNoNonOptionArgsPresent() {
		EnumerablePropertySource<?> ps = new SimpleCommandLinePropertySource("--o1=v1", "--o2");

		assertThat(ps.containsProperty("nonOptionArgs"), is(false));
		assertThat(ps.containsProperty("o1"), is(true));
		assertThat(ps.containsProperty("o2"), is(true));

		assertThat(ps.containsProperty("nonOptionArgs"), is(false));
		assertThat(ps.getProperty("nonOptionArgs"), nullValue());
		assertThat(ps.getPropertyNames().length, is(2));
	}
