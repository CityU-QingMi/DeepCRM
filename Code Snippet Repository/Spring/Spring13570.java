	@Test
	public void withCheckedObjectValue() throws Exception {
		this.tag.setPath("myFloat");
		this.tag.setValue(getFloat());
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);
		assertContainsAttribute(output, "name", "myFloat");
		assertContainsAttribute(output, "type", "radio");
		assertContainsAttribute(output, "value", getFloat().toString());
		assertContainsAttribute(output, "checked", "checked");
	}
