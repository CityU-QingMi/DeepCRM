	@Test
	public void simpleBindWithHtmlEscaping() throws Exception {
		final String NAME = "Rob \"I Love Mangos\" Harrop";
		final String HTML_ESCAPED_NAME = "Rob &quot;I Love Mangos&quot; Harrop";

		this.tag.setPath("name");
		this.rob.setName(NAME);

		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);

		assertContainsAttribute(output, "type", getType());
		assertValueAttribute(output, HTML_ESCAPED_NAME);
	}
