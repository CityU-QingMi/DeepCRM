	private void assertList(boolean selected) throws JspException, DocumentException {
		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		this.tag.setSize("5");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		validateOutput(output, selected);
		assertContainsAttribute(output, "size", "5");
	}
