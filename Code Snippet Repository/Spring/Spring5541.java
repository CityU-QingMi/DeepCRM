	@Test
	public void testReplaceFromSystemProperty() {
		System.setProperty("test.prop", "bar");
		try {
			String resolved = SystemPropertyUtils.resolvePlaceholders("${test.prop}");
			assertEquals("bar", resolved);
		}
		finally {
			System.getProperties().remove("test.prop");
		}
	}
