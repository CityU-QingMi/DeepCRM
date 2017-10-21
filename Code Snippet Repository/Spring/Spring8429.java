	@Test
	public void propertiesAreAvailableInEnvironment() {
		// Simple key/value pairs
		assertThat(property("foo"), is("bar"));
		assertThat(property("baz"), is("quux"));
		assertThat(property("enigma"), is("42"));

		// Values containing key/value delimiters (":", "=", " ")
		assertThat(property("x.y.z"), is("a=b=c"));
		assertThat(property("server.url"), is("http://example.com"));
		assertThat(property("key.value.1"), is("key=value"));
		assertThat(property("key.value.2"), is("key=value"));
		assertThat(property("key.value.3"), is("key:value"));
	}
