	@Test
	public void withNullResolvedCommand() throws Exception {
		try {
			tag.setModelAttribute(null);
			tag.doStartTag();
			fail("Must not be able to have a command name that resolves to null");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
