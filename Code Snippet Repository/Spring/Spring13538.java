	@Test
	public void asBodyTagWithEditor() throws Exception {
		String selectName = "testBean.stringArray";
		BindStatus bindStatus = new BindStatus(getRequestContext(), selectName, false) {
			@Override
			public PropertyEditor getEditor() {
				return new RulesVariantEditor();
			}
		};
		getPageContext().setAttribute(SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, bindStatus);

		RulesVariant rulesVariant = new RulesVariant("someRules", "someVariant");
		this.tag.setValue(rulesVariant);

		int result = this.tag.doStartTag();
		assertEquals(BodyTag.EVAL_BODY_BUFFERED, result);

		assertEquals(rulesVariant, getPageContext().getAttribute("value"));
		assertEquals(rulesVariant.toId(), getPageContext().getAttribute("displayValue"));

		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);
	}
