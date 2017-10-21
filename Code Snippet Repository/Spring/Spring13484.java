	private void assertWhenNoErrorsExistingMessagesInScopeAreNotClobbered(int scope) throws JspException {
		String existingAttribute = "something";
		getPageContext().setAttribute(ErrorsTag.MESSAGES_ATTRIBUTE, existingAttribute, scope);

		Errors errors = new BeanPropertyBindingResult(new TestBean(), "COMMAND_NAME");
		exposeBindingResult(errors);
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);

		String output = getOutput();
		assertEquals(0, output.length());

		assertEquals(existingAttribute, getPageContext().getAttribute(ErrorsTag.MESSAGES_ATTRIBUTE, scope));
	}
