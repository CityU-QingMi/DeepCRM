	@Test
	public void complexBind() throws Exception {
		String onselect = "doSelect()";

		this.tag.setPath("spouse.name");
		this.tag.setOnselect(onselect);

		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());
		String output = getOutput();
		assertContainsAttribute(output, "name", "spouse.name");
		assertContainsAttribute(output, "onselect", onselect);
		assertAttributeNotPresent(output, "readonly");
	}
