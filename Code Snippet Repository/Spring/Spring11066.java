	@Test
	public void patternPropertyGetCaptureCountTests() {
		// Test all basic section types
		assertEquals(1, parse("{foo}").getCapturedVariableCount());
		assertEquals(0, parse("foo").getCapturedVariableCount());
		assertEquals(1, parse("{*foobar}").getCapturedVariableCount());
		assertEquals(1, parse("/{*foobar}").getCapturedVariableCount());
		assertEquals(0, parse("/**").getCapturedVariableCount());
		assertEquals(1, parse("{abc}asdf").getCapturedVariableCount());
		assertEquals(1, parse("{abc}_*").getCapturedVariableCount());
		assertEquals(2, parse("{abc}_{def}").getCapturedVariableCount());
		assertEquals(0, parse("/").getCapturedVariableCount());
		assertEquals(0, parse("a?b").getCapturedVariableCount());
		assertEquals(0, parse("*").getCapturedVariableCount());

		// Test on full templates
		assertEquals(0, parse("/foo/bar").getCapturedVariableCount());
		assertEquals(1, parse("/{foo}").getCapturedVariableCount());
		assertEquals(2, parse("/{foo}/{bar}").getCapturedVariableCount());
		assertEquals(4, parse("/{foo}/{bar}_{goo}_{wibble}/abc/bar").getCapturedVariableCount());
	}
