	@Test
	@SuppressWarnings("")
	public void withJavaEnum() throws Exception {
		GenericBean testBean = new GenericBean();
		testBean.setCustomEnum(CustomEnum.VALUE_1);
		getPageContext().getRequest().setAttribute("testBean", testBean);
		String selectName = "testBean.customEnum";
		getPageContext().setAttribute(SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, new BindStatus(getRequestContext(), selectName, false));

		this.tag.setValue("VALUE_1");

		int result = this.tag.doStartTag();
		assertEquals(BodyTag.EVAL_BODY_BUFFERED, result);
		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);

		String output = getWriter().toString();

		assertOptionTagOpened(output);
		assertOptionTagClosed(output);
		assertContainsAttribute(output, "value", "VALUE_1");
		assertContainsAttribute(output, "selected", "selected");
	}
