	@Test
	public void ignoreUnresolvableNestedPlaceholdersIsConfigurable() {
		MutablePropertySources ps = new MutablePropertySources();
		ps.addFirst(new MockPropertySource()
			.withProperty("p1", "v1")
			.withProperty("p2", "v2")
			.withProperty("p3", "${p1}:${p2}:${bogus:def}") // unresolvable w/ default
			.withProperty("p4", "${p1}:${p2}:${bogus}")     // unresolvable placeholder
		);
		ConfigurablePropertyResolver pr = new PropertySourcesPropertyResolver(ps);
		assertThat(pr.getProperty("p1"), equalTo("v1"));
		assertThat(pr.getProperty("p2"), equalTo("v2"));
		assertThat(pr.getProperty("p3"), equalTo("v1:v2:def"));

		// placeholders nested within the value of "p4" are unresolvable and cause an
		// exception by default
		try {
			pr.getProperty("p4");
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex.getMessage(), containsString(
					"Could not resolve placeholder 'bogus' in value \"${p1}:${p2}:${bogus}\""));
		}

		// relax the treatment of unresolvable nested placeholders
		pr.setIgnoreUnresolvableNestedPlaceholders(true);
		// and observe they now pass through unresolved
		assertThat(pr.getProperty("p4"), equalTo("v1:v2:${bogus}"));

		// resolve[Nested]Placeholders methods behave as usual regardless the value of
		// ignoreUnresolvableNestedPlaceholders
		assertThat(pr.resolvePlaceholders("${p1}:${p2}:${bogus}"), equalTo("v1:v2:${bogus}"));
		try {
			pr.resolveRequiredPlaceholders("${p1}:${p2}:${bogus}");
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex.getMessage(), containsString(
					"Could not resolve placeholder 'bogus' in value \"${p1}:${p2}:${bogus}\""));
		}
	}
