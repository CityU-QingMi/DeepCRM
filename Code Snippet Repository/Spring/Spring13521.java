	@Test
	public void withErrors() throws Exception {
		this.tag.setPath("name");
		this.tag.setCssClass("good");
		this.tag.setCssErrorClass("bad");

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(this.rob, COMMAND_NAME);
		errors.rejectValue("name", "some.code", "Default Message");
		errors.rejectValue("name", "too.short", "Too Short");
		exposeBindingResult(errors);

		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);

		assertContainsAttribute(output, "type", getType());
		assertValueAttribute(output, "Rob");
		assertContainsAttribute(output, "class", "bad");
	}
