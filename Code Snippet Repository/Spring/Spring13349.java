	@Test
	public void nestedPathWithBindTag() throws JspException {
		PageContext pc = createPageContext();
		Errors errors = new ServletRequestDataBinder(new TestBean(), "tb").getBindingResult();
		pc.getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + "tb", errors);

		NestedPathTag nestedPathTag = new NestedPathTag();
		nestedPathTag.setPath("tb");
		nestedPathTag.setPageContext(pc);
		nestedPathTag.doStartTag();

		BindTag bindTag = new BindTag();
		bindTag.setPageContext(pc);
		bindTag.setPath("name");

		assertTrue("Correct doStartTag return value", bindTag.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		BindStatus status = (BindStatus) pc.getAttribute(BindTag.STATUS_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
		assertTrue("Has status variable", status != null);
		assertEquals("tb.name", status.getPath());
		assertEquals("Correct field value", "", status.getDisplayValue());

		BindTag bindTag2 = new BindTag();
		bindTag2.setPageContext(pc);
		bindTag2.setPath("age");

		assertTrue("Correct doStartTag return value", bindTag2.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		BindStatus status2 = (BindStatus) pc.getAttribute(BindTag.STATUS_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
		assertTrue("Has status variable", status2 != null);
		assertEquals("tb.age", status2.getPath());
		assertEquals("Correct field value", "0", status2.getDisplayValue());

		bindTag2.doEndTag();

		BindStatus status3 = (BindStatus) pc.getAttribute(BindTag.STATUS_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
		assertSame("Status matches previous status", status, status3);
		assertEquals("tb.name", status.getPath());
		assertEquals("Correct field value", "", status.getDisplayValue());

		bindTag.doEndTag();
		nestedPathTag.doEndTag();
	}
