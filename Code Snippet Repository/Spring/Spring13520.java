	@Test
	public void withNestedBindTagWithinForm() throws Exception {
		NestedPathTag nestedPathTag = new NestedPathTag();
		nestedPathTag.setPath("spouse.");
		nestedPathTag.setPageContext(getPageContext());
		nestedPathTag.doStartTag();

		BindTag bindTag = new BindTag();
		bindTag.setPath("name");
		bindTag.setPageContext(getPageContext());
		bindTag.doStartTag();

		BindStatus bindStatus = (BindStatus) getPageContext().findAttribute(BindTag.STATUS_VARIABLE_NAME);
		assertEquals("Sally", bindStatus.getValue());
	}
