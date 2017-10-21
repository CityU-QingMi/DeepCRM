	@Test
	public void messageWithVarAndScope() throws JspException {
		PageContext pc = createPageContext();
		MessageTag tag = new MessageTag();
		tag.setPageContext(pc);
		tag.setText("text & text");
		tag.setVar("testvar");
		tag.setScope("page");
		tag.doStartTag();
		assertEquals("Correct doEndTag return value", Tag.EVAL_PAGE, tag.doEndTag());
		assertEquals("text & text", pc.getAttribute("testvar"));
		tag.release();

		tag = new MessageTag();
		tag.setPageContext(pc);
		tag.setCode("test");
		tag.setVar("testvar2");
		tag.doStartTag();
		assertEquals("Correct doEndTag return value", Tag.EVAL_PAGE, tag.doEndTag());
		assertEquals("Correct message", "test message", pc.getAttribute("testvar2"));
		tag.release();
	}
