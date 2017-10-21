	@Test
	public void testReplaceFromSystemPropertyWithExpressionDefault() {
		System.setProperty("test.prop", "bar");
		try {
			String resolved = SystemPropertyUtils.resolvePlaceholders("${test.prop:#{foo.bar}}");
			assertEquals("bar", resolved);
		}
		finally {
			System.getProperties().remove("test.prop");
		}
	}
