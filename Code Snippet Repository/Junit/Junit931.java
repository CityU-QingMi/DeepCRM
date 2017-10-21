	@Test
	void parseValidClassSelectors() {
		// @formatter:off
		assertAll(
				() -> assertEquals(singletonList("com.acme.Foo"), parseArgLine("-c com.acme.Foo").getSelectedClasses()),
				() -> assertEquals(singletonList("com.acme.Foo"), parseArgLine("--c com.acme.Foo").getSelectedClasses()),
				() -> assertEquals(singletonList("com.acme.Foo"), parseArgLine("-select-class com.acme.Foo").getSelectedClasses()),
				() -> assertEquals(singletonList("com.acme.Foo"), parseArgLine("-select-class=com.acme.Foo").getSelectedClasses()),
				() -> assertEquals(singletonList("com.acme.Foo"), parseArgLine("--select-class com.acme.Foo").getSelectedClasses()),
				() -> assertEquals(singletonList("com.acme.Foo"), parseArgLine("--select-class=com.acme.Foo").getSelectedClasses()),
				() -> assertEquals(asList("com.acme.Foo", "com.example.Bar"), parseArgLine("-c com.acme.Foo -c com.example.Bar").getSelectedClasses())
		);
		// @formatter:on
	}
