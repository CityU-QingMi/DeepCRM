	@Test
	public void bindTagWithFieldButWithoutErrorsInstanceAndHtmlEscaping() throws JspException {
		PageContext pc = createPageContext();
		BindTag tag = new BindTag();
		tag.setPageContext(pc);
		tag.setPath("tb.name");
		tag.setHtmlEscape(true);
		pc.getRequest().setAttribute("tb", new TestBean("juergen&eva", 99));
		tag.doStartTag();
		BindStatus status = (BindStatus) pc.getAttribute(BindTag.STATUS_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
		assertEquals("name", status.getExpression());
		assertEquals("juergen&amp;eva", status.getValue());
	}
