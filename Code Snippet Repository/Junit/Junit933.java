	@Test
	void parseValidMethodSelectors() {
		// @formatter:off
		assertAll(
				() -> assertEquals(singletonList("com.acme.Foo#m()"), parseArgLine("-m com.acme.Foo#m()").getSelectedMethods()),
				() -> assertEquals(singletonList("com.acme.Foo#m()"), parseArgLine("--m com.acme.Foo#m()").getSelectedMethods()),
				() -> assertEquals(singletonList("com.acme.Foo#m()"), parseArgLine("-select-method com.acme.Foo#m()").getSelectedMethods()),
				() -> assertEquals(singletonList("com.acme.Foo#m()"), parseArgLine("-select-method=com.acme.Foo#m()").getSelectedMethods()),
				() -> assertEquals(singletonList("com.acme.Foo#m()"), parseArgLine("--select-method com.acme.Foo#m()").getSelectedMethods()),
				() -> assertEquals(singletonList("com.acme.Foo#m()"), parseArgLine("--select-method=com.acme.Foo#m()").getSelectedMethods()),
				() -> assertEquals(asList("com.acme.Foo#m()", "com.example.Bar#method(java.lang.Object)"),
						parseArgLine("-m com.acme.Foo#m() -m com.example.Bar#method(java.lang.Object)").getSelectedMethods())
		);
		// @formatter:on
	}
