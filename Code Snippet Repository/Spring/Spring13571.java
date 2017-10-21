	@Test
	public void withCheckedObjectValueAndEditor() throws Exception {
		this.tag.setPath("myFloat");
		this.tag.setValue("F12.99");

		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(this.bean, COMMAND_NAME);
		MyFloatEditor editor = new MyFloatEditor();
		bindingResult.getPropertyEditorRegistry().registerCustomEditor(Float.class, editor);
		getPageContext().getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + COMMAND_NAME, bindingResult);

		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);
		assertContainsAttribute(output, "name", "myFloat");
		assertContainsAttribute(output, "type", "radio");
		assertContainsAttribute(output, "value", "F" + getFloat().toString());
		assertContainsAttribute(output, "checked", "checked");
	}
