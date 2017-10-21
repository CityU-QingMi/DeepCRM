	@Test
	public void simpleBind() throws Exception {
		this.tag.setPath("name");
		this.tag.setReadonly(true);

		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());
		String output = getOutput();
		assertContainsAttribute(output, "name", "name");
		assertContainsAttribute(output, "readonly", "readonly");
		assertBlockTagContains(output, "Rob");
	}
