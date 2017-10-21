	@Test
	public void buttonTag() throws Exception {
		assertEquals(Tag.EVAL_BODY_INCLUDE, this.tag.doStartTag());
		assertEquals(Tag.EVAL_PAGE, this.tag.doEndTag());

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);

		assertContainsAttribute(output, "id", "My Id");
		assertContainsAttribute(output, "name", "My Name");
		assertContainsAttribute(output, "type", "submit");
		assertContainsAttribute(output, "value", "My Button");
		assertAttributeNotPresent(output, "disabled");
	}
