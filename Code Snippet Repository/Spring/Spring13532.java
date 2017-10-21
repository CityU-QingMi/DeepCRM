	@Test
	public void withCustomObjectNotSelected() throws Exception {
		String selectName = "testBean.someNumber";
		getPageContext().setAttribute(SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, new BindStatus(getRequestContext(), selectName, false));
		this.tag.setValue(new Float(12.35));
		this.tag.setLabel("GBP 12.35");
		int result = this.tag.doStartTag();
		assertEquals(BodyTag.EVAL_BODY_BUFFERED, result);
		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);

		String output = getOutput();

		assertOptionTagOpened(output);
		assertOptionTagClosed(output);
		assertContainsAttribute(output, "value", "12.35");
		assertAttributeNotPresent(output, "selected");
		assertBlockTagContains(output, "GBP 12.35");
	}
