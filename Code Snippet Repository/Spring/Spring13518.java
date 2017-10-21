	@Test
	public void complexBind() throws Exception {
		this.tag.setPath("spouse.name");

		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);

		assertContainsAttribute(output, "id", "spouse.name");
		assertContainsAttribute(output, "name", "spouse.name");
		assertContainsAttribute(output, "type", getType());
		assertValueAttribute(output, "Sally");
	}
