	@Test
	public void withUncheckedValue() throws Exception {
		this.tag.setPath("sex");
		this.tag.setValue("F");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);
		assertContainsAttribute(output, "name", "sex");
		assertContainsAttribute(output, "type", "radio");
		assertContainsAttribute(output, "value", "F");
		assertAttributeNotPresent(output, "checked");
	}
