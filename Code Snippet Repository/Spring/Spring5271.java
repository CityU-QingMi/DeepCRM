	@Test
	public void withOptionArgsOnly() {
		CommandLinePropertySource<?> ps =
			new SimpleCommandLinePropertySource("--o1=v1", "--o2");
		assertThat(ps.containsProperty("o1"), is(true));
		assertThat(ps.containsProperty("o2"), is(true));
		assertThat(ps.containsProperty("o3"), is(false));
		assertThat(ps.getProperty("o1"), equalTo("v1"));
		assertThat(ps.getProperty("o2"), equalTo(""));
		assertThat(ps.getProperty("o3"), nullValue());
	}
