	@Test
	public void simpleBindTagWithinForm() throws Exception {
		BindTag bindTag = new BindTag();
		bindTag.setPath("name");
		bindTag.setPageContext(getPageContext());
		bindTag.doStartTag();

		BindStatus bindStatus = (BindStatus) getPageContext().findAttribute(BindTag.STATUS_VARIABLE_NAME);
		assertEquals("Rob", bindStatus.getValue());
	}
