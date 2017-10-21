	@Test
	public void testNullIsIllegalArgument() {
		try {
			new JndiTemplateEditor().setAsText(null);
			fail("Null is illegal");
		}
		catch (IllegalArgumentException ex) {
			// OK
		}
	}
