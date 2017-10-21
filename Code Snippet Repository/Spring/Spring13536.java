	@Test
	public void asBodyTagSelected() throws Exception {
		String selectName = "testBean.name";
		BindStatus bindStatus = new BindStatus(getRequestContext(), selectName, false);
		getPageContext().setAttribute(SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, bindStatus);

		String bodyContent = "some content";

		this.tag.setValue("Rob Harrop");
		int result = this.tag.doStartTag();
		assertEquals(BodyTag.EVAL_BODY_BUFFERED, result);
		this.tag.setBodyContent(new MockBodyContent(bodyContent, getWriter()));
		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);

		String output = getOutput();
		assertOptionTagOpened(output);
		assertOptionTagClosed(output);
		assertBlockTagContains(output, bodyContent);
	}
