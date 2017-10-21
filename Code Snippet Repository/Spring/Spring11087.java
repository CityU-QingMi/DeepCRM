	@Test
	public void multipleSeparatorsInPattern() {
		PathPattern pp = parse("a//b//c");
		assertEquals("Literal(a) Separator(/) Separator(/) Literal(b) Separator(/) Separator(/) Literal(c)",pp.toChainString());
		assertMatches(pp,"a//b//c");
		assertEquals("Literal(a) Separator(/) WildcardTheRest(/**)",parse("a//**").toChainString());
		checkMatches("///abc", "///abc");
		checkNoMatch("///abc", "/abc");
		checkNoMatch("//", "/");
		checkMatches("//", "//");
		checkNoMatch("///abc//d/e", "/abc/d/e");
		checkMatches("///abc//d/e", "///abc//d/e");
		checkNoMatch("///abc//{def}//////xyz", "/abc/foo/xyz");
		checkMatches("///abc//{def}//////xyz", "///abc//p//////xyz");
	}
