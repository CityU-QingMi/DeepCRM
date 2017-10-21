	@Test
	public void toStringTests() {
		assertEquals("CaptureTheRest(/{*foobar})", checkStructure("/{*foobar}").toChainString());
		assertEquals("CaptureVariable({foobar})", checkStructure("{foobar}").toChainString());
		assertEquals("Literal(abc)", checkStructure("abc").toChainString());
		assertEquals("Regex({a}_*_{b})", checkStructure("{a}_*_{b}").toChainString());
		assertEquals("Separator(/)", checkStructure("/").toChainString());
		assertEquals("SingleCharWildcarded(?a?b?c)", checkStructure("?a?b?c").toChainString());
		assertEquals("Wildcard(*)", checkStructure("*").toChainString());
		assertEquals("WildcardTheRest(/**)", checkStructure("/**").toChainString());
	}
