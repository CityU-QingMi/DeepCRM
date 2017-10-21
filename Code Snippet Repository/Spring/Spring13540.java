	@Test
	public void optionTagNotNestedWithinSelectTag() throws Exception {
		try {
			tag.setParent(null);
			tag.setValue("foo");
			tag.doStartTag();
			fail("Must throw an IllegalStateException when not nested within a <select/> tag.");
		}
		catch (IllegalStateException ex) {
			// expected
		}
	}
