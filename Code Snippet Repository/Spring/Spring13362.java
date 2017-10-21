	@Test
	public void bindTagWithMappedProperties() throws JspException {
		PageContext pc = createPageContext();
		IndexedTestBean tb = new IndexedTestBean();
		Errors errors = new ServletRequestDataBinder(tb, "tb").getBindingResult();
		errors.rejectValue("map[key1]", "code1", "message1");
		errors.rejectValue("map[key1]", "code2", "message2");
		pc.getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + "tb", errors);

		BindTag tag = new BindTag();
		tag.setPageContext(pc);
		tag.setPath("tb.map[key1]");
		assertTrue("Correct doStartTag return value", tag.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		BindStatus status = (BindStatus) pc.getAttribute(BindTag.STATUS_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
		assertTrue("Has status variable", status != null);
		assertTrue("Correct expression", "map[key1]".equals(status.getExpression()));
		assertTrue("Value is TestBean", status.getValue() instanceof TestBean);
		assertTrue("Correct value", "name4".equals(((TestBean) status.getValue()).getName()));
		assertTrue("Correct isError", status.isError());
		assertTrue("Correct errorCodes", status.getErrorCodes().length == 2);
		assertTrue("Correct errorMessages", status.getErrorMessages().length == 2);
		assertTrue("Correct errorCode", "code1".equals(status.getErrorCodes()[0]));
		assertTrue("Correct errorCode", "code2".equals(status.getErrorCodes()[1]));
		assertTrue("Correct errorMessage", "message1".equals(status.getErrorMessages()[0]));
		assertTrue("Correct errorMessage", "message2".equals(status.getErrorMessages()[1]));
	}
