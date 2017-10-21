	@Test
	public void starMatchesAllErrors() throws Exception {
		this.tag.setPath("*");
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
		assertTrue(output.contains("field error"));
	}
