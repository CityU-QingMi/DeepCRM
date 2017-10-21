	@Test
	public void aliasEventReceived() throws Exception {
		List aliases = this.eventListener.getAliases("testBean");
		assertEquals(2, aliases.size());
		AliasDefinition aliasDefinition1 = (AliasDefinition) aliases.get(0);
		assertEquals("testBeanAlias1", aliasDefinition1.getAlias());
		assertTrue(aliasDefinition1.getSource() instanceof Element);
		AliasDefinition aliasDefinition2 = (AliasDefinition) aliases.get(1);
		assertEquals("testBeanAlias2", aliasDefinition2.getAlias());
		assertTrue(aliasDefinition2.getSource() instanceof Element);
	}
