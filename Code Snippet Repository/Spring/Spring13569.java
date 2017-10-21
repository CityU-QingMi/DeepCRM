	@Test
	public void withCheckedValueAndDynamicAttributes() throws Exception {
		this.tag.setPath("sex");
		this.tag.setValue("M");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);
		assertContainsAttribute(output, "name", "sex");
		assertContainsAttribute(output, "type", "radio");
		assertContainsAttribute(output, "value", "M");
		assertContainsAttribute(output, "checked", "checked");
	}
