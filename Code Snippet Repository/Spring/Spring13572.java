	@Test
	public void withUncheckedObjectValue() throws Exception {
		Float value = new Float("99.45");
		this.tag.setPath("myFloat");
		this.tag.setValue(value);
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);
		assertContainsAttribute(output, "name", "myFloat");
		assertContainsAttribute(output, "type", "radio");
		assertContainsAttribute(output, "value", value.toString());
		assertAttributeNotPresent(output, "checked");
	}
