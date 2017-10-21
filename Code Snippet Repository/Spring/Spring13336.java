	@Test
	public void bindTagWithToStringAndHtmlEscaping() throws JspException {
		PageContext pc = createPageContext();
		BindTag tag = new BindTag();
		tag.setPageContext(pc);
		tag.setPath("tb.doctor");
		tag.setHtmlEscape(true);
		TestBean tb = new TestBean("somebody", 99);
		NestedTestBean ntb = new NestedTestBean("juergen&eva");
		tb.setDoctor(ntb);
		pc.getRequest().setAttribute("tb", tb);
		tag.doStartTag();
		BindStatus status = (BindStatus) pc.getAttribute(BindTag.STATUS_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
		assertEquals("doctor", status.getExpression());
		assertTrue(status.getValue() instanceof NestedTestBean);
		assertTrue(status.getDisplayValue().indexOf("juergen&amp;eva") != -1);
	}
