	@Test
	public void messageWithVar() throws JspException {
		PageContext pc = createPageContext();
		MessageTag tag = new MessageTag();
		tag.setPageContext(pc);
		tag.setText("text & text");
		tag.setVar("testvar");
		tag.doStartTag();
		assertEquals("Correct doEndTag return value", Tag.EVAL_PAGE, tag.doEndTag());
		assertEquals("text & text", pc.getAttribute("testvar"));
		tag.release();

		// try to reuse
		tag.setPageContext(pc);
		tag.setCode("test");
		tag.setVar("testvar");

		tag.doStartTag();
		assertEquals("Correct doEndTag return value", Tag.EVAL_PAGE, tag.doEndTag());
		assertEquals("Correct message", "test message", pc.getAttribute("testvar"));
	}
