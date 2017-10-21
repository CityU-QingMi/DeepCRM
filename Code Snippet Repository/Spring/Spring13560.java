	@Test
	public void passwordValueIsNotRenderedByDefault() throws Exception {
		this.getTag().setPath("name");

		assertEquals(Tag.SKIP_BODY, this.getTag().doStartTag());

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);

		assertContainsAttribute(output, "type", getType());
		assertValueAttribute(output, "");
	}
