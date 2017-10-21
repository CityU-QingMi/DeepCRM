	@Test
	public void messageTagWithCodeAndNestedArguments() throws JspException {
		PageContext pc = createPageContext();
		final StringBuffer message = new StringBuffer();
		MessageTag tag = new MessageTag() {
			@Override
			protected void writeMessage(String msg) {
				message.append(msg);
			}
		};
		tag.setPageContext(pc);
		tag.setCode("testArgs");
		assertTrue("Correct doStartTag return value", tag.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		tag.addArgument("arg1");
		tag.addArgument(6);
		assertEquals("Correct doEndTag return value", Tag.EVAL_PAGE, tag.doEndTag());
		assertEquals("Correct message", "test arg1 message 6", message.toString());
	}
