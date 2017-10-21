	@Test
	public void dynamicTypeAttribute() throws JspException {
		this.tag.setPath("myFloat");
		this.tag.setDynamicAttribute(null, "type", "number");

		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);

		assertContainsAttribute(output, "type", "number");
		assertValueAttribute(output, "12.34");
	}
