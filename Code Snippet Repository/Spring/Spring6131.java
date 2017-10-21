	@Test
	public void testStandardTypeLocator() {
		StandardTypeLocator tl = new StandardTypeLocator();
		List<String> prefixes = tl.getImportPrefixes();
		assertEquals(1, prefixes.size());
		tl.registerImport("java.util");
		prefixes = tl.getImportPrefixes();
		assertEquals(2, prefixes.size());
		tl.removeImport("java.util");
		prefixes = tl.getImportPrefixes();
		assertEquals(1, prefixes.size());
	}
