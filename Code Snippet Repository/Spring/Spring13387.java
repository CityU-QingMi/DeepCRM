	@Test
	@SuppressWarnings("")
	public void nullMessageSource() throws JspException {
		PageContext pc = createPageContext();
		ConfigurableWebApplicationContext ctx = (ConfigurableWebApplicationContext)
				RequestContextUtils.findWebApplicationContext((HttpServletRequest) pc.getRequest(), pc.getServletContext());
		ctx.close();

		MessageTag tag = new MessageTag();
		tag.setPageContext(pc);
		tag.setCode("test");
		tag.setVar("testvar2");
		tag.doStartTag();
		assertEquals("Correct doEndTag return value", Tag.EVAL_PAGE, tag.doEndTag());
	}
