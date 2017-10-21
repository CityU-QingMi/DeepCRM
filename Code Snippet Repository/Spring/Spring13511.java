	@Test
	public void readOnlyAttributeRenderingWhenReadonlyIsTrue() throws Exception {
		this.tag.setPath("name");
		this.tag.setReadonly(true);

		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);

		assertContainsAttribute(output, "type", getType());
		assertContainsAttribute(output, "readonly", "readonly");
		assertValueAttribute(output, "Rob");
	}
