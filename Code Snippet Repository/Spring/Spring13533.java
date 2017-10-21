	@Test
	public void withCustomObjectAndEditorSelected() throws Exception {
		final PropertyEditor floatEditor = new SimpleFloatEditor();
		floatEditor.setValue(new Float("12.34"));
		String selectName = "testBean.someNumber";
		BindStatus bindStatus = new BindStatus(getRequestContext(), selectName, false) {
			@Override
			public PropertyEditor getEditor() {
				return floatEditor;
			}
		};
		getPageContext().setAttribute(SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, bindStatus);

		this.tag.setValue(new Float(12.34));
		this.tag.setLabel("12.34f");

		int result = this.tag.doStartTag();
		assertEquals(BodyTag.EVAL_BODY_BUFFERED, result);
		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);

		String output = getOutput();
		assertOptionTagOpened(output);
		assertOptionTagClosed(output);
		assertContainsAttribute(output, "selected", "selected");
		assertBlockTagContains(output, "12.34f");
	}
