	@Test
	public void omittedPathMatchesObjectErrorsOnly() throws Exception {
		this.tag.setPath(null);
		Errors errors = new BeanPropertyBindingResult(new TestBean(), "COMMAND_NAME");
		errors.reject("some.code", "object error");
		errors.rejectValue("name", "some.code", "field error");
		exposeBindingResult(errors);
		this.tag.doStartTag();
		assertNotNull(getPageContext().getAttribute(ErrorsTag.MESSAGES_ATTRIBUTE));
		this.tag.doEndTag();
		String output = getOutput();
		assertTrue(output.contains("id=\"testBean.errors\""));
		assertTrue(output.contains("object error"));
		assertFalse(output.contains("field error"));
	}
