	@Test
	public void withUnderscore() {
		envMap.put("a_key", "a_value");

		assertThat(ps.containsProperty("a_key"), equalTo(true));
		assertThat(ps.containsProperty("a.key"), equalTo(true));

		assertThat(ps.getProperty("a_key"), equalTo((Object)"a_value"));
		assertThat( ps.getProperty("a.key"), equalTo((Object)"a_value"));
	}
