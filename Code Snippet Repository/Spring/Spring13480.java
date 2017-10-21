	@Test
	public void asBodyTagWithErrorsAndExistingMessagesAttributeInNonPageScopeAreNotClobbered() throws Exception {
		String existingAttribute = "something";
		getPageContext().setAttribute(ErrorsTag.MESSAGES_ATTRIBUTE, existingAttribute, PageContext.APPLICATION_SCOPE);
		Errors errors = new BeanPropertyBindingResult(new TestBean(), "COMMAND_NAME");
		errors.rejectValue("name", "some.code", "Default Message");
		errors.rejectValue("name", "too.short", "Too Short");
		exposeBindingResult(errors);
		int result = this.tag.doStartTag();
		assertEquals(BodyTag.EVAL_BODY_BUFFERED, result);
		assertNotNull(getPageContext().getAttribute(ErrorsTag.MESSAGES_ATTRIBUTE));
		assertTrue(getPageContext().getAttribute(ErrorsTag.MESSAGES_ATTRIBUTE) instanceof List);
		String bodyContent = "Foo";
		this.tag.setBodyContent(new MockBodyContent(bodyContent, getWriter()));
		this.tag.doEndTag();
		this.tag.doFinally();
		assertEquals(bodyContent, getOutput());
		assertEquals(existingAttribute,
				getPageContext().getAttribute(ErrorsTag.MESSAGES_ATTRIBUTE, PageContext.APPLICATION_SCOPE));
	}
