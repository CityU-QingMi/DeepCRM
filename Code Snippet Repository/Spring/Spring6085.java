	@Test
	public void testImports() throws EvaluationException {
		StandardTypeLocator locator = new StandardTypeLocator();
		assertEquals(Integer.class,locator.findType("java.lang.Integer"));
		assertEquals(String.class,locator.findType("java.lang.String"));

		List<String> prefixes = locator.getImportPrefixes();
		assertEquals(1,prefixes.size());
		assertTrue(prefixes.contains("java.lang"));
		assertFalse(prefixes.contains("java.util"));

		assertEquals(Boolean.class,locator.findType("Boolean"));
		// currently does not know about java.util by default
//		assertEquals(java.util.List.class,locator.findType("List"));

		try {
			locator.findType("URL");
			fail("Should have failed");
		}
		catch (EvaluationException ee) {
			SpelEvaluationException sEx = (SpelEvaluationException)ee;
			assertEquals(SpelMessage.TYPE_NOT_FOUND,sEx.getMessageCode());
		}
		locator.registerImport("java.net");
		assertEquals(java.net.URL.class,locator.findType("URL"));
	}
